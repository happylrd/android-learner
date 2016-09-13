package com.happylrd.geoquiz;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private static final String EXTRA_ANSWER_IS_TRUE = "com.happylrd.geoquiz.answer_is_true";
    private static final String EXTRA_ANSWER_SHOWN = "com.happylrd.geoquiz.answer_shown";

    private boolean mAnswerIsTrue;

    private TextView tv_answer;
    private Button btn_show_answer;

    public static Intent newIntent(Context packageContext, boolean answerIsTrue) {
        Intent intent = new Intent(packageContext, CheatActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        return intent;
    }

    public static boolean wasAnswerShown(Intent result) {
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        initView();
        initListener();
    }

    private void initView() {
        tv_answer = (TextView) findViewById(R.id.tv_answer);
        btn_show_answer = (Button) findViewById(R.id.btn_show_answer);
    }

    private void initListener() {
        btn_show_answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAnswerIsTrue) {
                    tv_answer.setText(R.string.true_button);
                } else {
                    tv_answer.setText(R.string.false_button);
                }
                setAnswerShownResult(true);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    int cx = btn_show_answer.getWidth() / 2;
                    int cy = btn_show_answer.getHeight() / 2;
                    float radius = btn_show_answer.getWidth();
                    Animator anim = ViewAnimationUtils
                            .createCircularReveal(btn_show_answer, cx, cy, radius, 0);
                    anim.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            btn_show_answer.setVisibility(View.INVISIBLE);
                        }
                    });
                    anim.start();
                } else {
                    btn_show_answer.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }
}
