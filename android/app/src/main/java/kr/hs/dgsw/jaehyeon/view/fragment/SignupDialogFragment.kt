package kr.hs.dgsw.jaehyeon.view.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import kr.hs.dgsw.jaehyeon.R
import kr.hs.dgsw.jaehyeon.databinding.FragmentSignupDialogBinding
import kr.hs.dgsw.jaehyeon.viewModel.SignupViewModel
import android.widget.Toast
import kr.hs.dgsw.jaehyeon.view.activity.ManagerActivity

class SignupDialogFragment : DialogFragment() {

    lateinit var binding: FragmentSignupDialogBinding
    lateinit var viewModel: SignupViewModel

    fun getInstance(): SignupDialogFragment {
        return SignupDialogFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_signup_dialog, container, false)
        viewModel = ViewModelProvider(this).get(SignupViewModel::class.java)
        binding.vm = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    fun observeViewModel() {
        with(viewModel) {
            clickCancelBtn.observe(this@SignupDialogFragment) {
                dismiss()
            }
            clickCompleteBtn.observe(this@SignupDialogFragment) {
                if(firstPassword.value?.length != 0 && secondPassword.value?.length == 4){
                    savePasswordSharedPreference(firstPassword.value, secondPassword.value)
                    val intent = Intent(this@SignupDialogFragment.context, ManagerActivity::class.java)
                    startActivity(intent)
                    dismiss()
                } else {
                    Toast.makeText(this@SignupDialogFragment.context, "조건에 맞게 비밀번호를 설정해주세요.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun savePasswordSharedPreference(firstPw: String?, secondPw: String?){
        val sharedPreferences = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        with (sharedPreferences.edit()) {
            putString("firstPw1", firstPw)
            putString("secondPw2", secondPw)
            commit()
        }
    }
}