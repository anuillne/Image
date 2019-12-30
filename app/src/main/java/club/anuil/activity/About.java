package club.anuil.activity;

import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import club.anuil.R;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        TextView textView = findViewById(R.id.textViewAbout);
        //设置TextView可滑动
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());
    }
}
