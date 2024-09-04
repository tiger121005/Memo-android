package app.ito.yomi.memo

interface NoticeDialogListener {
    fun onDialogPositiveClick(dialog: DeleteDialogFragment)
    fun onDialogNegativeClick(dialog: DeleteDialogFragment)
}