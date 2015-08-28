package com.github.florent37.mobileleanback;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.florent37.mobileleanback.mobileleanback.MobileLeanBack;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    MobileLeanBack mobileLeanBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mobileLeanBack = (MobileLeanBack) findViewById(R.id.mobileLeanBack);

        mobileLeanBack.setCustomizer(new MobileLeanBack.Customizer() {
            @Override
            public void customizeTitle(TextView textView) {
                textView.setTypeface(null, Typeface.BOLD);
            }
        });
        Picasso.with(getApplicationContext())
                .load("http://www.journaldugeek.com/wp-content/blogs.dir/1/files/2015/01/game-of-thrones-saison-5-documentaire.jpg")
                .fit().centerCrop()
                .into(mobileLeanBack.getImageBackground());

        mobileLeanBack.setAdapter(new MobileLeanBack.Adapter<TestViewHolder>() {
            @Override
            public int getLineCount() {
                return 10;
            }

            @Override
            public int getCellsCount(int line) {
                return 10;
            }

            @Override
            public TestViewHolder onCreateViewHolder(ViewGroup viewGroup, int line) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell_test, viewGroup, false);
                return new TestViewHolder(view);
            }

            @Override
            public void onBindViewHolder(TestViewHolder viewHolder, int i) {
                viewHolder.textView.setText("test " + i);

                String url = "http://www.lorempixel.com/40" + viewHolder.row + "/40" + viewHolder.cell + "/";
                Picasso.with(viewHolder.imageView.getContext()).load(url).into(viewHolder.imageView);
            }

            @Override
            public String getTitleForRow(int row) {
                return "Line "+row;
            }

        });
    }
}