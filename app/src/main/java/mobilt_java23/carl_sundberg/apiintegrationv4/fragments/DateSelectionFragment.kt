package mobilt_java23.carl_sundberg.apiintegrationv4.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import androidx.fragment.app.Fragment
import mobilt_java23.carl_sundberg.apiintegrationv4.R
import java.time.LocalDate


class DateSelectionFragment : Fragment() {

    private var selectedDate: LocalDate? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_date_selection, container, false)


        val datePicker = view.findViewById<DatePicker>(R.id.datePicker)


        var year = 0;
        var month = 0
        var day = 0
        datePicker.init(year, month, day) { _, year, month, day ->

            selectedDate = LocalDate.of(year, month + 1, day)
        }


        val confirmButton = view.findViewById<Button>(R.id.confirm_button)
        confirmButton.setOnClickListener {

            selectedDate?.let {
                val result = Bundle().apply { putString("selectedDate", it.toString()) }
                parentFragmentManager.setFragmentResult("dateSelection", result)
            }
        }

        return view
    }
}
