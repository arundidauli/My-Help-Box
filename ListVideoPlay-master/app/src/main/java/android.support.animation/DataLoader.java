package android.support.animation;




import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class DataLoader {
    public static void loaddata(Context context, String key, String value){
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }
    public static String deleverdata(Context context,String key){

        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        return sharedPreferences.getString(key,null);
    }
    public static void cleardata(Context context){

        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        editor.commit();


    }
}
