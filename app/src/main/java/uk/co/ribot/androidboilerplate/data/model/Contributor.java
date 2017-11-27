package uk.co.ribot.androidboilerplate.data.model;

/**
 * Created by wangweijun1 on 2017/11/27.
 */

public class Contributor {
    public String login;
    public int contributions;

    @Override
    public String toString() {
        return "login:"+login+", contributions:"+contributions;
    }
}
