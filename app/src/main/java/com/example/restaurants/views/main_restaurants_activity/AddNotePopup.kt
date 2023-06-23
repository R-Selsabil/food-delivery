import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.PopupWindow
import androidx.fragment.app.Fragment
import com.example.restaurants.R

class AddNotePopup(context: Fragment) : PopupWindow(context.requireContext()) {

    init {
        // inflate the layout
        val view =
            LayoutInflater.from(context.requireContext()).inflate(R.layout.layout_popup_notes, null)

        // Set the width and height of the popup window to be half of the screen size
        val displayMetrics = DisplayMetrics()
        context.requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)
        val width = (displayMetrics.widthPixels * 0.9).toInt()
        val height = WindowManager.LayoutParams.WRAP_CONTENT
        setWidth(width)
        setHeight(height)
        // Set the background of the popup window to be transparent
        setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // set the content view of the popup window
        contentView = view


        // set the focusable property of the popup window to true
        isFocusable = true


        // find the views in the layout
        val confirmButton = view.findViewById<Button>(R.id.button)
        val cancelButton = view.findViewById<Button>(R.id.button2)
        val noteInput = view.findViewById<EditText>(R.id.note_input)

        // set a click listener for the confirm button
        confirmButton.setOnClickListener {
            val note = noteInput.text.toString()
            // do something with the note, such as saving it to a database
            dismiss()
        }

        // set a click listener for the cancel button
        cancelButton.setOnClickListener {
            dismiss()
        }
    }
}
