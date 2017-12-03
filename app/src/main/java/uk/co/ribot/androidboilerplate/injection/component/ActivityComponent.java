package uk.co.ribot.androidboilerplate.injection.component;

import dagger.Subcomponent;
import uk.co.ribot.androidboilerplate.injection.PerActivity;
import uk.co.ribot.androidboilerplate.injection.module.ActivityModule;
import uk.co.ribot.androidboilerplate.ui.main.MainActivity;
import uk.co.ribot.androidboilerplate.ui.ribot.RibotListActivity;

/**
 * This component inject dependencies to all Activities across the application
 *
 * ActivityComponent  --继承--> ConfigPersistentComponent --继承--> ApplicationComponent
 */
@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

    void inject(RibotListActivity ribotListActivity);
}
