package br.com.luislabs.conversaonline.auth.presentation.register

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import br.com.luislabs.auth.utils.toast
import br.com.luislabs.conversaonline.R
import br.com.luislabs.conversaonline.auth.model.UserRegister
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.lang.Exception

class RegisterFragment : Fragment(R.layout.fragment_register), RegisterContract.View {

    private var userRegister: UserRegister? = null
    private var uriPhoto: Uri = Uri.EMPTY

    private val presenter: RegisterContract.Presenter = RegisterPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.initializeFirebaseAuth()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        register_button.setOnClickListener {
            userRegister = UserRegister(
                email = editEmail.text.toString(),
                password = editPassword.text.toString(),
                name = editName.text.toString(),
                photoUri = uriPhoto
            )

            registerUser(userRegister!!)
        }

        iconProfile.setOnClickListener {
            getPhotoUri()
        }
    }

    override fun getPhotoUri() {
        val photoPickerIntent = Intent(Intent.ACTION_PICK)
        photoPickerIntent.type = "image/*"
        startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == RESULT_LOAD_IMG) {
            CoroutineScope(Dispatchers.IO).launch {
                withContext(Dispatchers.IO) {
                    try {
                        val imageUri: Uri = data?.data ?: Uri.EMPTY
                        val imageStream: InputStream? = requireContext().contentResolver
                            .openInputStream(
                                imageUri
                            )
                        val selectedImage: Bitmap = BitmapFactory.decodeStream(imageStream)
                        iconProfile.setImageBitmap(selectedImage)
                    } catch (e: Exception) {
                        toast(getString(R.string.falha_ao_tentar_carregar_imagem))
                    }
                }
            }
        } else {
            toast(getString(R.string.falha_ao_tentar_carregar_imagem))
        }
    }

    override fun registerUser(userRegister: UserRegister) {
        presenter.registerUser(userRegister)
    }

    override fun registeredSuccessfully() {
        toast(getString(R.string.cadastro_realizado_com_sucesso))
    }

    override fun emptyEmailOrPassword() {
        editEmail.error = getString(R.string.campo_vazio)
        editPassword.error = getString(R.string.campo_vazio)
    }

    override fun emptyEmail() {
        editEmail.error = getString(R.string.campo_vazio)
    }

    override fun emptyPassword() {
        editPassword.error = getString(R.string.campo_vazio)
    }

    override fun errorWhenRegisteringUser(exception: Exception?) {
        toast(getString(R.string.erro_ao_cadastrar_usuario))
    }

    companion object {
        private const val RESULT_LOAD_IMG = 43

        fun newInstance(): RegisterFragment {
            return RegisterFragment()
        }
    }
}