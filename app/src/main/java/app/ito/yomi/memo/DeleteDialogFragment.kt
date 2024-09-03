package app.ito.yomi.memo

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment


class DeleteDialogFragment : DialogFragment() {

    lateinit var listener: NoticeDialogListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage("削除しますか？")
                .setPositiveButton("OK") { dialog, id ->
                    whichButtonClicked(dialog, id)

                }
                .setNegativeButton("キャンセル") { dialog, id ->
                    whichButtonClicked(dialog, id)
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as NoticeDialogListener
        } catch (e: java.lang.ClassCastException) {
            throw ClassCastException( (context.toString() + "must implimnet NoticeDialogListener"))
        }
    }

    fun whichButtonClicked(dialog: DialogInterface, which: Int) {
        when (which) {
            DialogInterface.BUTTON_POSITIVE -> listener.onDialogPositiveClick(this)
            DialogInterface.BUTTON_NEGATIVE -> listener.onDialogNegativeClick(this)
        }
    }

}