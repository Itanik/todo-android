package me.itanik.todo.presentation.note.dialogs

import android.app.Dialog
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import timber.log.Timber
import java.util.*

class TimePickerDialog(private val initialDate: Date? = null) : DialogFragment(),
    android.app.TimePickerDialog.OnTimeSetListener {

    /**
     * Lambda called when the user is done setting a new time and the dialog has closed.
     *
     * First param is a hour.
     * Second param is a minute.
     */
    var onTimeSet: ((Int, Int) -> Unit)? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        Calendar.getInstance().apply {
            if (initialDate != null)
                time = initialDate

            return android.app.TimePickerDialog(
                activity,
                this@TimePickerDialog,
                get(Calendar.HOUR_OF_DAY),
                get(Calendar.MINUTE),
                DateFormat.is24HourFormat(activity)
            )
        }
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        onTimeSet?.let { it(hourOfDay, minute) }
        Timber.d("Time set: hour=$hourOfDay, minute=$minute")
    }
}