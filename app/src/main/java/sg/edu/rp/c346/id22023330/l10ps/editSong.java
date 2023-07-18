package sg.edu.rp.c346.id22023330.l10ps;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class editSong extends AppCompatActivity {
    Button cancel, update, delete;
    EditText title, singers, year;
    RadioGroup stars;
    Song rID, rTitle, rSingers, rYear, rStars;
    TextView id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        id = findViewById(R.id.ID);
        title = findViewById(R.id.title);
        singers = findViewById(R.id.singers);
        year = findViewById(R.id.year);
        cancel = findViewById(R.id.btnCancel);
        stars = findViewById(R.id.stars);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);

        Intent i = getIntent();

        rID = (Song) i.getSerializableExtra("ID");
        id.setText(String.valueOf(rID.getId()));
        rTitle = (Song) i.getSerializableExtra("Title");
        title.setText(rTitle.getTitle());
        rSingers = (Song) i.getSerializableExtra("Singers");
        singers.setText(rSingers.getSingers());
        rYear = (Song) i.getSerializableExtra("Year");
        year.setText(String.valueOf(rYear.getYear()));
        rStars = (Song) i.getSerializableExtra("Stars");


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(editSong.this);
                String stTitle = String.valueOf(title.getText());
                String stSingers = String.valueOf(singers.getText());
                int iYear = Integer.valueOf(String.valueOf(year.getText()));
                int starss = 0;
                int checkedRadioId = stars.getCheckedRadioButtonId();

                if(checkedRadioId == R.id.star1){
                    starss += 1;
                }
                else if (checkedRadioId == R.id.star2){
                    starss += 2;
                }
                else if (checkedRadioId == R.id.star3){
                    starss += 3;
                }
                else if (checkedRadioId == R.id.star4){
                    starss += 4;
                }
                else if (checkedRadioId == R.id.star5){
                    starss += 5;
                }


                rTitle.setSongTitle(stTitle);
                rSingers.setSongSingers(stSingers);
                rYear.setSongYear(iYear);
                rStars.setSongStars(starss);

                db.updateSong(rTitle,rSingers,rYear,rStars);
                db.close();
                finish();


            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(editSong.this);
                db.deleteSong(rID.getId());
                finish();
            }
        });

    }
}
