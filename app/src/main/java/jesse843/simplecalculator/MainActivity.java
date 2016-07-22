package jesse843.simplecalculator;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //initializing variables
        previous = "0";
        displayTextView  = (TextView)findViewById(R.id.displayTextView);
        multiply = false;
        divide = false;
        add = false;
        subtract = false;
        showingAnswer = false;
        alreadyClear = false;
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

    public void digitOne (View v) {
        digitAdd("1");
    }

    public void digitTwo (View v) {
        digitAdd("2");
    }

    public void digitThree (View v) {
        digitAdd("3");
    }

    public void digitFour (View v) {
        digitAdd("4");
    }

    public void digitFive (View v) {
        digitAdd("5");
    }

    public void digitSix (View v) {
        digitAdd("6");
    }

    public void digitSeven (View v) {
        digitAdd("7");
    }

    public void digitEight (View v) {
        digitAdd("8");
    }

    public void digitNine (View v) {
        digitAdd("9");
    }

    public void digitZero (View v) {
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

        if (displayTextView.getText().toString().contains(".")) {
            displayTextView.setText(""+displayTextView.getText().toString()+digit);
        }
        else {
            BigInteger displayNum = new BigInteger(displayTextView.getText().toString());
            displayNum = displayNum.multiply(new BigInteger("10"));
            displayNum = displayNum.add(new BigInteger(digit));
            displayTextView.setText(""+displayNum);
        }
    }

    public void addDecimal (View v) {
        if (!displayTextView.getText().toString().contains(".")) {
            displayTextView.setText(displayTextView.getText().toString() + ".");
        }
    }

    public void clearDisplay (View v) {
        displayTextView.setText("0");
    }

    public void backspace (View v) {
        if (displayTextView.getText().toString().length() > 1)
            displayTextView.setText(displayTextView.getText().toString().substring(0, displayTextView.getText().toString().length()-1));
        else
            displayTextView.setText("0");
    }

    public void negate (View v) {
        if (!displayTextView.getText().toString().contains(".")) {
            BigInteger displayNum = new BigInteger(displayTextView.getText().toString());
            displayNum = displayNum.negate();
            displayTextView.setText(""+displayNum);
        }
        else {
            BigDecimal displayNum = new BigDecimal(displayTextView.getText().toString());
            displayNum = displayNum.negate();
            displayTextView.setText(""+displayNum);
        }
    }

    public void multiply (View v) {
        if (!divide && !add && !subtract && !displayTextView.getText().toString().equals("0")) {
            previous = displayTextView.getText().toString();
            multiply = true;
        }
    }

    public void divide (View v) {
        if (!multiply && !add && !subtract && !displayTextView.getText().toString().equals("0")) {
            previous = displayTextView.getText().toString();
            divide = true;
        }
    }

    public void add (View v) {
        if (!divide && !multiply && !subtract && !displayTextView.getText().toString().equals("0")) {
            previous = displayTextView.getText().toString();
            add = true;
        }
    }

    public void subtract (View v) {
        if (!divide && !add && !multiply && !displayTextView.getText().toString().equals("0")) {
            previous = displayTextView.getText().toString();
            subtract = true;
        }
    }

    public void equals (View v) {
        if (!displayTextView.getText().toString().equals("0") && (multiply || divide || add || subtract)) {
            BigDecimal result = new BigDecimal("0");
            if (multiply) {
                result = new BigDecimal(previous);
                result = result.multiply(new BigDecimal(displayTextView.getText().toString()));
                multiply = false;
            }
            else if (divide) {
                result = new BigDecimal(previous);
                result = result.divide(new BigDecimal(displayTextView.getText().toString()), 5, RoundingMode.HALF_UP);
                divide = false;
                String tooLong = result.toString();
                while (tooLong.charAt(tooLong.length()-1) == '0' && tooLong.length() > 1) {
                    tooLong = tooLong.substring(0, tooLong.length()-1);
                    Log.v("DDDDDDDDDDDDD", tooLong);
                }
                result = new BigDecimal(tooLong);
            }
            else if (add) {
                result = new BigDecimal(previous);
                result = result.add(new BigDecimal(displayTextView.getText().toString()));
                add = false;
            }
            else if (subtract) {
                result = new BigDecimal(previous);
                result = result.subtract(new BigDecimal(displayTextView.getText().toString()));
                subtract = false;
            }
            displayTextView.setText(""+result);
        }
        showingAnswer = true;
        alreadyClear = false;
    }
    public void squareRoot (View v) {

    }
}
