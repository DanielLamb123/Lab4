package my.edu.taruc.lab4;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SettingsActivity extends AppCompatActivity {
    private EditText editTextName;
    private RadioGroup radioGroupGender;
    private RadioButton radioButtonMale,radioButtonFemale;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        editTextName=findViewById(R.id.editTextName);
        radioButtonMale=findViewById(R.id.radioButtonMale);
        radioButtonFemale=findViewById(R.id.radioButtonFemale);
        radioGroupGender=findViewById(R.id.radioGroupGender);

    }
    @Override
    protected void onResume(){
        super.onResume();
        sharedPreferences=getSharedPreferences(PREF_FILE,MODE_PRIVATE);
        String name;
        int gender;
        name=sharedPreferences.getString("user_name","");
        gender=sharedPreferences.getInt("user_gender",-1);
        editTextName.setText(name);
        if(gender==1){
            radioButtonMale.setChecked(true);
        }
        else if(gender==0){
            radioButtonFemale.setChecked(true);
        }

    }
    @Override
    protected void onPause(){
        super.onPause();
        SharedPreferences.Editor editor=sharedPreferences.edit();
        String name;
        int gender;
        name=editTextName.getText().toString();
        editor.putString("user_name",name);
        gender=radioGroupGender.getCheckedRadioButtonId();
        if(gender==R.id.radioButtonMale){
            editor.putInt("user_gender",1);
        }
        else if(gender==R.id.radioButtonFemale){
            editor.putInt("user_gender",0);
        }
        editor.apply();
    }
}
