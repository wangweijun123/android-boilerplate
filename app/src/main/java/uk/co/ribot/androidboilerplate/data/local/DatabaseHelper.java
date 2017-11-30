package uk.co.ribot.androidboilerplate.data.local;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.VisibleForTesting;
import android.util.Log;

import com.squareup.sqlbrite2.BriteDatabase;
import com.squareup.sqlbrite2.SqlBrite;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import uk.co.ribot.androidboilerplate.data.model.Contributor;
import uk.co.ribot.androidboilerplate.data.model.Ribot;

@Singleton
public class DatabaseHelper {

    private final BriteDatabase mDb;

    @Inject
    public DatabaseHelper(DbOpenHelper dbOpenHelper) {
        this(dbOpenHelper, Schedulers.io());
    }

    @VisibleForTesting
    public DatabaseHelper(DbOpenHelper dbOpenHelper, Scheduler scheduler) {
        SqlBrite.Builder briteBuilder = new SqlBrite.Builder();
        mDb = briteBuilder.build().wrapDatabaseHelper(dbOpenHelper, scheduler);
    }

    public BriteDatabase getBriteDb() {
        return mDb;
    }

    public Observable<Ribot> setRibots(final Collection<Ribot> newRibots) {
        return Observable.create(new ObservableOnSubscribe<Ribot>() {
            @Override
            public void subscribe(ObservableEmitter<Ribot> emitter) throws Exception {
                if (emitter.isDisposed()) return;
                BriteDatabase.Transaction transaction = mDb.newTransaction();
                try {
                    mDb.delete(Db.RibotProfileTable.TABLE_NAME, null);
                    for (Ribot ribot : newRibots) {
                        long result = mDb.insert(Db.RibotProfileTable.TABLE_NAME,
                                Db.RibotProfileTable.toContentValues(ribot.profile()),
                                SQLiteDatabase.CONFLICT_REPLACE);
                        if (result >= 0) emitter.onNext(ribot);
                    }
                    transaction.markSuccessful();
                    emitter.onComplete();
                } finally {
                    transaction.end();
                }
            }
        });
    }

    public Observable<Contributor> setContributors(final Collection<Contributor> newContributors) {
        return Observable.create(new ObservableOnSubscribe<Contributor>() {
            @Override
            public void subscribe(ObservableEmitter<Contributor> emitter) throws Exception {
                if (emitter.isDisposed()) return;
                BriteDatabase.Transaction transaction = mDb.newTransaction();
                try {
                    mDb.delete(Db.RibotProfileTable.TABLE_NAME_CONTRIBUTOR, null);
                    for (Contributor contributor : newContributors) {
                        Log.i("wang", "tid:"+Thread.currentThread().getId()+", mDb:"+mDb + " insert contributor:"+contributor);
                        long result = mDb.insert(Db.RibotProfileTable.TABLE_NAME_CONTRIBUTOR,
                                Db.RibotProfileTable.toContentValues(contributor),
                                SQLiteDatabase.CONFLICT_REPLACE);
                        if (result >= 0) {
                            Log.i("wang", " emitter 发射 contributor:"+contributor);
                            emitter.onNext(contributor);
                        }
                    }
                    transaction.markSuccessful();
                    emitter.onComplete();
                } finally {
                    transaction.end();
                }
            }
        });
    }

    public Observable<List<Ribot>> getRibots() {
        return mDb.createQuery(Db.RibotProfileTable.TABLE_NAME,
                "SELECT * FROM " + Db.RibotProfileTable.TABLE_NAME)
                .mapToList(new Function<Cursor, Ribot>() {
                    @Override
                    public Ribot apply(@NonNull Cursor cursor) throws Exception {
                        return Ribot.create(Db.RibotProfileTable.parseCursor(cursor));
                    }
                });
    }

    public Observable<List<Contributor>> getContributors() {
        return mDb.createQuery(Db.RibotProfileTable.TABLE_NAME_CONTRIBUTOR,
                "SELECT * FROM " + Db.RibotProfileTable.TABLE_NAME_CONTRIBUTOR)
                .mapToList(new Function<Cursor, Contributor>() {
                    @Override
                    public Contributor apply(@NonNull Cursor cursor) throws Exception {
                        return Db.RibotProfileTable.parseCursor2(cursor);
                    }
                });
    }

}
