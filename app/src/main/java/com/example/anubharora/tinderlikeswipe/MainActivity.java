package com.example.anubharora.tinderlikeswipe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.LinearLayout.LayoutParams;

import com.example.anubharora.tinderlikeswipe.model.UserModel;

import java.util.ArrayList;
import java.util.Collections;


public class MainActivity extends AppCompatActivity {

    private int screenWidth, screenCenter, x_coordinate, y_coordinate, x, y, moveAction;
    private RelativeLayout parentLayout, containerLayout;
    private ImageView containerImage;
    private TextView mTextView;

    private ArrayList<UserModel> arlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        parentLayout = (RelativeLayout) findViewById(R.id.parent_layout);
        screenWidth = getWindowManager().getDefaultDisplay().getWidth();
        screenCenter = screenWidth / 2;

        arlist = new ArrayList<>();
        getArrayData();

        populateData();
    }

    private void populateData() {

        for (int i = 0; i < arlist.size(); i++) {
            final View container = LayoutInflater.from(MainActivity.this).inflate(R.layout.tinder_item_layout, null);

            containerImage = container.findViewById(R.id.img_view);
            containerLayout = container.findViewById(R.id.rl_container_layout);
            mTextView = container.findViewById(R.id.txt_view_name);

            LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            container.setLayoutParams(layoutParams);
            containerImage.setBackgroundResource(arlist.get(i).getPicture());
            mTextView.setText(arlist.get(i).getName());

            containerLayout.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    x_coordinate = (int) motionEvent.getRawX();
                    y_coordinate = (int) motionEvent.getRawY();

                    container.setX(0);
                    container.setY(0);

                    switch (motionEvent.getAction()) {
                        case MotionEvent.ACTION_DOWN: //when the container is touched
                            x = (int) motionEvent.getX();
                            y = (int) motionEvent.getY();
                            break;

                        case MotionEvent.ACTION_MOVE: //when the container is moved
                            x_coordinate = (int) motionEvent.getRawX();
                            y_coordinate = (int) motionEvent.getRawY();
                            container.setX(x_coordinate - x);
                            container.setY(y_coordinate - y);

                            //container is moved right
                            if (x_coordinate >= screenCenter) {
                                container.setRotation((float) ((x_coordinate - screenCenter) * (Math.PI / 32)));
                                if (x_coordinate > (screenCenter + (screenCenter / 2))) {
                                    if (x_coordinate > (screenWidth - (screenCenter / 4))) {
                                        moveAction = 2;
                                    } else {
                                        moveAction = 0;
                                    }
                                } else {
                                    moveAction = 0;
                                }
                            } else { //container is moved left
                                container.setRotation((float) ((x_coordinate - screenCenter) * (Math.PI / 32)));
                                if (x_coordinate < (screenCenter / 2)) {
                                    if (x_coordinate < screenCenter / 4) {
                                        moveAction = 1;
                                    } else {
                                        moveAction = 0;
                                    }
                                } else {
                                    moveAction = 0;
                                }
                            }
                            break;
                        case MotionEvent.ACTION_UP:

                            if (moveAction == 0) { //noaction
                                container.setX(0);
                                container.setY(0);
                                container.setRotation(0);
                            } else if (moveAction == 1) { //leftswipe
                                Toast.makeText(MainActivity.this, "UNLIKE", Toast.LENGTH_SHORT).show();
                                parentLayout.removeView(container);
                            } else if (moveAction == 2) { //rightswipe
                                Toast.makeText(MainActivity.this, "LIKE", Toast.LENGTH_SHORT).show();
                                parentLayout.removeView(container);
                            }
                            break;
                        default:
                            break;
                    }
                    return true;
                }
            });
            parentLayout.addView(container);
        }
    }

    private void getArrayData() {
        UserModel model1 = new UserModel();
        model1.setName("Alphabet A ");
        model1.setPicture(R.drawable.a);
        arlist.add(model1);

        UserModel model2 = new UserModel();
        model2.setName("Alphabet B ");
        model2.setPicture(R.drawable.b);
        arlist.add(model2);

        UserModel model3 = new UserModel();
        model3.setName("Alphabet D ");
        model3.setPicture(R.drawable.d);
        arlist.add(model3);

        UserModel model4 = new UserModel();
        model4.setName("Alphabet E ");
        model4.setPicture(R.drawable.e);
        arlist.add(model4);

        UserModel model5 = new UserModel();
        model5.setName("Alphabet H ");
        model5.setPicture(R.drawable.h);
        arlist.add(model5);

        UserModel model6 = new UserModel();
        model6.setName("Alphabet L ");
        model6.setPicture(R.drawable.l);
        arlist.add(model6);

        UserModel model7 = new UserModel();
        model7.setName("Alphabet O ");
        model7.setPicture(R.drawable.o);
        arlist.add(model7);

        UserModel model8 = new UserModel();
        model8.setName("Alphabet P ");
        model8.setPicture(R.drawable.p);
        arlist.add(model8);

        UserModel model9 = new UserModel();
        model9.setName("Alphabet R ");
        model9.setPicture(R.drawable.r);
        arlist.add(model9);

        UserModel model10 = new UserModel();
        model10.setName("Alphabet S ");
        model10.setPicture(R.drawable.s);
        arlist.add(model10);

        Collections.reverse(arlist);
    }


}
