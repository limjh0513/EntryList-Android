package kr.hs.dgsw.jaehyeon.view.fragment

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import kr.hs.dgsw.jaehyeon.R
import kr.hs.dgsw.jaehyeon.databinding.FragmentWriteBinding
import kr.hs.dgsw.jaehyeon.viewModel.WriteViewModel

class WriteDialogFragment : DialogFragment() {

    lateinit var binding: FragmentWriteBinding
    lateinit var viewModel: WriteViewModel

    fun getInstance(): WriteDialogFragment {
        return WriteDialogFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_write, container, false)
        viewModel = ViewModelProvider(this).get(WriteViewModel::class.java)

        binding.lifecycleOwner = this
        binding.activity = this
        binding.vm = viewModel
        isCancelable = false

        return binding.root
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        val activity = activity
        if (activity is DialogInterface.OnDismissListener) {
            (activity as DialogInterface.OnDismissListener).onDismiss(dialog)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    fun observeViewModel() {
        with(viewModel) {
            clickCancelBtn.observe(this@WriteDialogFragment) {
                dismiss()
            }
            errorInputNumber.observe(this@WriteDialogFragment){
                Toast.makeText(this@WriteDialogFragment.context, "예시와 맞는 전화번호를 입력했는지 확인해주세요.", Toast.LENGTH_SHORT).show()
            }
            errorInputResidence.observe(this@WriteDialogFragment){
                Toast.makeText(this@WriteDialogFragment.context, "예시와 맞는 거주지를 입력했는지 확인해주세요.", Toast.LENGTH_SHORT).show()
            }
            errorNotChecked.observe(this@WriteDialogFragment){
                Toast.makeText(this@WriteDialogFragment.context, "개인정보 수집 및 제공 동의에 체크해주세요.", Toast.LENGTH_SHORT).show()
            }
            writeResult.observe(this@WriteDialogFragment){
                dismiss()
            }
        }
    }
}