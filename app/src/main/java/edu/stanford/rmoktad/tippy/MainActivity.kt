package edu.stanford.rmoktad.tippy

import android.animation.ArgbEvaluator
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.SeekBar
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

private const val TAG = "MainActivity"
private  const val INITIAL_TIP_PERCENT = 15
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        seekBarTip.progress = INITIAL_TIP_PERCENT
        tvTipPercent.text = "$INITIAL_TIP_PERCENT%"
        updateTipDescription(INITIAL_TIP_PERCENT)
        seekBarTip.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                Log.i(TAG, "onProgressChanged $progress")
                tvTipPercent.text = "$progress%"
                computeTipAndTotal()
                updateTipDescription(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        etBase.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                Log.i(TAG, "afterTextChanged $s")
                computeTipAndTotal()
                checkTotal()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        etBudgetAmount.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                Log.i(TAG, "afterTextChanged $s")
                checkTotal()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }

    private fun checkTotal() {
        //if the total/budget is empty, we don't need to check if it's under budget!
        if(tvTotalAmount.text.toString().isEmpty() || etBudgetAmount.text.toString().isEmpty()){
            tvTotalAmount.setTextColor(Color.DKGRAY)
            return;
        }

        val total = tvTotalAmount.text.toString().toDouble()
        val budget = etBudgetAmount.text.toString().toDouble()

        if(total <= budget){
            tvTotalAmount.setTextColor(Color.DKGRAY)
        }

        else{
            tvTotalAmount.setTextColor(Color.RED)
        }
    }

    private fun updateTipDescription(tipPercent: Int) {
        val tipDescription : String
        when(tipPercent){
            in 0..9 -> tipDescription= "Poor \uD83D\uDE1E"
            in 10..14 -> tipDescription = "Acceptable \uD83D\uDE10"
            in 15..19 -> tipDescription = "Good \uD83D\uDE42"
            in 20..24 -> tipDescription = "Great \uD83D\uDE0A"
            else -> tipDescription = "Amazing \uD83E\uDD73"
        }

        tvTipDescription.text = tipDescription

        //gets the color on the spectrum for tip on the range of tip percentages (0 - 30)
        val color = ArgbEvaluator().evaluate(tipPercent.toFloat()/seekBarTip.max,
            ContextCompat.getColor(this, R.color.colorWorstTip),
            ContextCompat.getColor(this, R.color.colorBestTip)) as Int
        tvTipDescription.setTextColor(color)
    }

    private fun computeTipAndTotal(){
        if (etBase.text.isEmpty()){  //if the base amount is empty
            //clear out fields and do not calculate
            tvTipAmount.text = ""
            tvTotalAmount.text = ""
            return
        }
        val baseAmount = etBase.text.toString().toDouble()
        val tipPercent = seekBarTip.progress

        val tipAmount = baseAmount * tipPercent / 100
        val totalAmount = baseAmount + tipAmount

        //formatted to show only 2 decimal places
        tvTipAmount.text = "%.2f".format(tipAmount)
        tvTotalAmount.text = "%.2f".format(totalAmount)
    }
}
