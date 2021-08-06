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
import kr.hs.dgsw.jaehyeon.databinding.FragmentLoginDialogBinding
import kr.hs.dgsw.jaehyeon.view.activity.ManagerActivity
import kr.hs.dgsw.jaehyeon.viewModel.LoginViewModel

class LoginDialogFragment : DialogFragment() {

    lateinit var binding: FragmentLoginDialogBinding
    lateinit var viewModel: LoginViewModel

    fun getInstance(): LoginDialogFragment {
        return LoginDialogFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login_dialog, container, false)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.vm = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    fun observeViewModel(){
        with(viewModel){
            cancelOnclick.observe(this@LoginDialogFragment){
                dismiss()
            }
            completeOnclick.observe(this@LoginDialogFragment){
                checkPw(firstPwInput.value, secondPwInput.value)
            }
        }
    }

    private fun checkPw(firstPwInput: String?, secondPwInput: String?) {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        val firstPw = sharedPref.getString("firstPw1", null)
        val secondPw = sharedPref.getString("secondPw2", null)

        if(firstPw.equals(firstPwInput) && secondPw.equals(secondPwInput)){
            val intent = Intent(this.context, ManagerActivity::class.java)
            startActivity(intent)
            dismiss()
        }
    }
}