package h4rar.space.converter;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText sourceNum;
    EditText sourceBase;
    EditText endBase;
    TextView answer;
    TextView _2_ans;
    TextView _3_ans;
    TextView _8_ans;
    TextView _10_ans;
    TextView _16_ans;
    ImageButton buttonClearSourceNum;
    ImageButton buttonRevers;
    ImageButton buttonClearAll;
    ImageButton buttonAnswer;

    String result;
    String inTwo;
    String inThree;
    String inEight;
    String inTen;
    String inSixteen;

    ImageView cat;
    TextView error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sourceNum = findViewById(R.id.sourceNum);
        sourceBase = findViewById(R.id.sourceBase);
        endBase = findViewById(R.id.endBase);
        answer = findViewById(R.id.answer);
        _2_ans = findViewById(R.id._2_ans);
        _3_ans = findViewById(R.id._3_ans);
        _8_ans = findViewById(R.id._8_ans);
        _10_ans = findViewById(R.id._10_ans);
        _16_ans = findViewById(R.id._16_ans);
        buttonClearSourceNum = findViewById(R.id.clear_sourceNum);
        buttonRevers = findViewById(R.id.revers);
        buttonClearAll = findViewById(R.id.clear_all);
        buttonAnswer = findViewById(R.id.button_answer);
        cat = findViewById(R.id.cat);
        error = findViewById(R.id.error);

        buttonClearSourceNum.setOnTouchListener(listener);
        buttonRevers.setOnTouchListener(listener);
        buttonClearAll.setOnTouchListener(listener);
        buttonAnswer.setOnTouchListener(listener);
    }

    View.OnTouchListener listener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (v.getId()) {
                case R.id.clear_sourceNum:
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        Drawable backgroundClick = getResources().getDrawable(R.drawable.ic_action_clear_click);
                        buttonClearSourceNum.setBackground(backgroundClick);
                        return true;
                    } else if (event.getAction() == MotionEvent.ACTION_UP) {
                        Drawable backgroundClick = getResources().getDrawable(R.drawable.ic_action_clear);
                        buttonClearSourceNum.setBackground(backgroundClick);
                        sourceNum.getText().clear();
                        return true;
                    }
                    break;

                case R.id.revers:
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        Drawable backgroundClick = getResources().getDrawable(R.drawable.ic_action_reverse_click);
                        buttonRevers.setBackground(backgroundClick);

                        String sBase = sourceBase.getText().toString();
                        String eBase = endBase.getText().toString();
                        sourceBase.setText(eBase);
                        endBase.setText(sBase);
                        return true;
                    } else if (event.getAction() == MotionEvent.ACTION_UP) {
                        Drawable backgroundClick = getResources().getDrawable(R.drawable.ic_action_reverse);
                        buttonRevers.setBackground(backgroundClick);
                        return true;
                    }
                    break;

                case R.id.clear_all:
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        Drawable backgroundClick = getResources().getDrawable(R.drawable.ic_action_clear_all_click);
                        buttonClearAll.setBackground(backgroundClick);
                        sourceNum.getText().clear();
                        sourceBase.getText().clear();
                        endBase.getText().clear();
                        answer.setText("");
                        return true;
                    } else if (event.getAction() == MotionEvent.ACTION_UP) {
                        Drawable backgroundClick = getResources().getDrawable(R.drawable.ic_action_clear_all);
                        buttonClearAll.setBackground(backgroundClick);
                        return true;
                    }
                    break;

                case R.id.button_answer:
                    try {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                            Drawable backgroundClick = getResources().getDrawable(R.drawable.ic_action_answer_click);
                            buttonAnswer.setBackground(backgroundClick);

                            int sNum = Integer.parseInt(sourceNum.getText().toString());
                            int sBase = Integer.parseInt(sourceBase.getText().toString());
                            int eBase = Integer.parseInt(endBase.getText().toString());
                            Convert convert = new Convert();
                            Map ans = convert.getAnswer(sNum, sBase, eBase);
                            result = (String) ans.get("ans");
                            inTwo = (String) ans.get("2");
                            inThree = (String) ans.get("3");
                            inEight = (String) ans.get("8");
                            inTen = (String) ans.get("10");
                            inSixteen = (String) ans.get("16");
                            answer.setText(result);
                            _2_ans.setText(inTwo);
                            _3_ans.setText(inThree);
                            _8_ans.setText(inEight);
                            _10_ans.setText(inTen);
                            _16_ans.setText(inSixteen);
                            cat.setVisibility(View.INVISIBLE);
                            error.setVisibility(View.INVISIBLE);
                            return true;
                        } else if (event.getAction() == MotionEvent.ACTION_UP) {
                            Drawable backgroundClick = getResources().getDrawable(R.drawable.ic_action_answer);
                            buttonAnswer.setBackground(backgroundClick);
                            return true;
                        }
                        break;
                    } catch (NumberFormatException e) {
                        cat.setVisibility(View.VISIBLE);
                        error.setVisibility(View.VISIBLE);
                        answer.setText("");
                        _2_ans.setText("");
                        _3_ans.setText("");
                        _8_ans.setText("");
                        _10_ans.setText("");
                        _16_ans.setText("");
//                        Toast toast = Toast.makeText(getApplicationContext(),
//                                R.string.error, Toast.LENGTH_SHORT);
//                        toast.show();
                    } finally {
                        break;
                    }
            }
            return false;
        }
    };
}