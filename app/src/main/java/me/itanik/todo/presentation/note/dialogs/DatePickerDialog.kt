package me.itanik.todo.presentation.note.dialogs

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import timber.log.Timber
import java.util.*

class DatePickerDialog(private val initialDate: Date? = null) : DialogFragment(),
    DatePickerDialog.OnDateSetListener {
    private var year: Int? = null
    private var month: Int? = null
    private var day: Int? = null

    /**
     * Lambda called when the user is done setting a new date and the dialog has closed.
     *
     * First param is an year.
     * Second param is a month.
     * Third param is a day.
     */
    var onDateSet: ((Int, Int, Int) -> Unit)? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        Calendar.getInstance().apply {
            if (initialDate != null)
                time = initialDate

            return DatePickerDialog(
                requireContext(),
                this@DatePickerDialog,
                year ?: get(Calendar.YEAR),
                month ?: get(Calendar.MONTH),
                day ?: get(Calendar.DAY_OF_MONTH)
            )
        }
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        this.year = year
        this.month = month
        this.day = day

        onDateSet?.let { it(year, month, day) }
        Timber.d("Date set: year=$year, month=$month, day=$day")
    }
}