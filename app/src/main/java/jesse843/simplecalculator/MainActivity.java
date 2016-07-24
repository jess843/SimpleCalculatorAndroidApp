package jesse843.simplecalculator;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ImageSpan;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class MainActivity extends AppCompatActivity {
    String previous;
    private TextView displayTextView;
    private boolean multiply;
    private boolean divide;
    private boolean add;
    private boolean subtract;
    private boolean showingAnswer = false;
    private boolean alreadyClear = false;
    private TextView multiplyTextView;
    private TextView divideTextView;
    private TextView addTextView;
    private TextView subtractTextView;
    private ScrollView scrollview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //initializing variables
        previous = "0";
        displayTextView = (TextView) findViewById(R.id.displayTextView);
        multiply = false;
        divide = false;
        add = false;
        subtract = false;
        showingAnswer = false;
        alreadyClear = false;
        multiplyTextView = (TextView) findViewById(R.id.multiply_button);
        divideTextView = (TextView) findViewById(R.id.divide_button);
        addTextView = (TextView) findViewById(R.id.add_button);
        subtractTextView = (TextView) findViewById(R.id.subtract_button);
        scrollview = ((ScrollView) findViewById(R.id.scrollView));

        scrollview.post(new Runnable() {

            @Override
            public void run() {
                scrollview.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void digitOne(View v) {
        digitAdd("1");
    }

    public void digitTwo(View v) {
        digitAdd("2");
    }

    public void digitThree(View v) {
        digitAdd("3");
    }

    public void digitFour(View v) {
        digitAdd("4");
    }

    public void digitFive(View v) {
        digitAdd("5");
    }

    public void digitSix(View v) {
        digitAdd("6");
    }

    public void digitSeven(View v) {
        digitAdd("7");
    }

    public void digitEight(View v) {
        digitAdd("8");
    }

    public void digitNine(View v) {
        digitAdd("9");
    }

    public void digitZero(View v) {
        digitAdd("0");
    }

    public void digitAdd(String digit) {
        if ((multiply || divide || add || subtract) && !alreadyClear) {
            displayTextView.setText("0");
            alreadyClear = true;
        }
        if (showingAnswer) {
            displayTextView.setText("0");
        }
        showingAnswer = false;

        displayTextView.setTextColor(Color.WHITE);

        if (displayTextView.getText().toString().contains(".")) {
            displayTextView.setText("" + displayTextView.getText().toString() + digit);
        } else {
            BigInteger displayNum = new BigInteger(displayTextView.getText().toString());
            displayNum = displayNum.multiply(new BigInteger("10"));
            displayNum = displayNum.add(new BigInteger(digit));
            displayTextView.setText("" + displayNum);
        }
        scrollview.post(new Runnable() {

            @Override
            public void run() {
                scrollview.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
    }

    public void addDecimal(View v) {
        if ((multiply || divide || add || subtract) && !alreadyClear) {
            displayTextView.setText("0");
            alreadyClear = true;
        }
        if (showingAnswer) {
            displayTextView.setText("0");
        }
        showingAnswer = false;
        displayTextView.setTextColor(Color.WHITE);
        if ((multiply || divide || add || subtract) && !alreadyClear) {
            displayTextView.setText("0");
            alreadyClear = true;
        }
        if (!displayTextView.getText().toString().contains(".")) {
            displayTextView.setText(displayTextView.getText().toString() + ".");
        }
        scrollview.post(new Runnable() {

            @Override
            public void run() {
                scrollview.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
    }

    public void clearDisplay(View v) {
        displayTextView.setText("0");
        if (multiply) {
            multiplyTextView.setTextColor(Color.BLACK);
            multiply = false;
        } else if (divide) {
            divideTextView.setTextColor(Color.BLACK);
            divide = false;
        } else if (add) {
            addTextView.setTextColor(Color.BLACK);
            add = false;
        } else if (subtract) {
            subtractTextView.setTextColor(Color.BLACK);
            subtract = false;
        }

        showingAnswer = false;
        displayTextView.setTextColor(Color.WHITE);
        alreadyClear = false;
        previous = "0";
    }

    public void backspace(View v) {
        if (!showingAnswer) {
            if (displayTextView.getText().toString().length() > 1)
                displayTextView.setText(displayTextView.getText().toString().substring(0, displayTextView.getText().toString().length() - 1));
            else {
                displayTextView.setText("0");
            }
        }
    }

    public void negate(View v) {
        if (!showingAnswer && alreadyClear) {
            if (!displayTextView.getText().toString().contains(".")) {
                BigInteger displayNum = new BigInteger(displayTextView.getText().toString());
                displayNum = displayNum.negate();
                displayTextView.setText("" + displayNum);
            } else {
                BigDecimal displayNum = new BigDecimal(displayTextView.getText().toString());
                displayNum = displayNum.negate();
                displayTextView.setText("" + displayNum);
            }
        }
        scrollview.post(new Runnable() {

            @Override
            public void run() {
                scrollview.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
    }

    public void multiply(View v) {
        if (!multiply && !divide && !add && !subtract) {
            previous = displayTextView.getText().toString();
            multiply = true;
            multiplyTextView.setTextColor(Color.RED);
            alreadyClear = false;
        } else if (alreadyClear) {
            equals(null);
            previous = displayTextView.getText().toString().substring(1, displayTextView.getText().toString().length());
            multiply = true;
            multiplyTextView.setTextColor(Color.RED);
            displayTextView.setTextColor(Color.WHITE);
            alreadyClear = false;
        }
    }

    public void divide(View v) {
        if (!divide && !multiply && !add && !subtract && !displayTextView.getText().toString().equals("0")) {
            previous = displayTextView.getText().toString();
            divide = true;
            divideTextView.setTextColor(Color.RED);
            alreadyClear = false;
        } else if (alreadyClear) {
            equals(null);
            previous = displayTextView.getText().toString().substring(1, displayTextView.getText().toString().length());
            divide = true;
            divideTextView.setTextColor(Color.RED);
            displayTextView.setTextColor(Color.WHITE);
            alreadyClear = false;
        }
    }

    public void add(View v) {
        if (!add && !divide && !multiply && !subtract) {
            previous = displayTextView.getText().toString();
            add = true;
            addTextView.setTextColor(Color.RED);
            alreadyClear = false;
        } else if (alreadyClear) {
            equals(null);
            previous = displayTextView.getText().toString().substring(1, displayTextView.getText().toString().length());
            add = true;
            addTextView.setTextColor(Color.RED);
            displayTextView.setTextColor(Color.WHITE);
            alreadyClear = false;
        }
    }

    public void subtract(View v) {
        if (!subtract && !divide && !add && !multiply) {
            previous = displayTextView.getText().toString();
            subtract = true;
            subtractTextView.setTextColor(Color.RED);
            alreadyClear = false;
        } else if (alreadyClear) {
            equals(null);
            previous = displayTextView.getText().toString().substring(1, displayTextView.getText().toString().length());
            subtract = true;
            subtractTextView.setTextColor(Color.RED);
            displayTextView.setTextColor(Color.WHITE);
            alreadyClear = false;
        }
    }

    public void equals(View v) {
        if (multiply || divide || add || subtract) {
            BigDecimal result = new BigDecimal("0");
            if (previous.charAt(0) == '=') {
                previous = previous.substring(1, previous.length());
            }
            if (multiply) {
                result = new BigDecimal(previous);
                result = result.multiply(new BigDecimal(displayTextView.getText().toString()));
                multiply = false;
                multiplyTextView.setTextColor(Color.BLACK);
            } else if (divide) {
                if (displayTextView.getText().toString().equals(("0"))) {
                    return;
                }
                result = new BigDecimal(previous);
                result = result.divide(new BigDecimal(displayTextView.getText().toString()), 5, RoundingMode.HALF_UP);
                divide = false;
                String tooLong = result.toString();
                while (tooLong.charAt(tooLong.length() - 1) == '0' && tooLong.length() > 1) {
                    tooLong = tooLong.substring(0, tooLong.length() - 1);
                }
                result = new BigDecimal(tooLong);
                divideTextView.setTextColor(Color.BLACK);
            } else if (add) {
                result = new BigDecimal(previous);
                result = result.add(new BigDecimal(displayTextView.getText().toString()));
                add = false;
                addTextView.setTextColor(Color.BLACK);
            } else if (subtract) {
                result = new BigDecimal(previous);
                result = result.subtract(new BigDecimal(displayTextView.getText().toString()));
                subtract = false;
                subtractTextView.setTextColor(Color.BLACK);
            }
            displayTextView.setText("=" + result);

            showingAnswer = true;
            alreadyClear = true;

            displayTextView.setTextColor(Color.YELLOW);
            scrollview.post(new Runnable() {

                @Override
                public void run() {
                    scrollview.fullScroll(ScrollView.FOCUS_DOWN);
                }
            });
        }
    }
}
