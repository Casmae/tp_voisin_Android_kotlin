package fr.mbds.android.neighbors.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import fr.mbds.android.NavigationListener
import fr.mbds.android.neighbors.R
import fr.mbds.android.neighbors.data.NeighborRepository
import fr.mbds.android.neighbors.models.Neighbor

class AddNeighbourFragment : Fragment(), TextWatcher {

    private lateinit var image: TextInputEditText
    private lateinit var name: TextInputEditText
    private lateinit var phone: TextInputEditText
    private lateinit var webSite: TextInputEditText
    private lateinit var addressMail: TextInputEditText
    private lateinit var aboutMe: TextInputEditText
    private lateinit var addNeighbor: MaterialButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.add_neighbor, container, false)

        (activity as? NavigationListener)?.let {
            it.updateTitle(R.string.addNeighbor_title)
        }

        image = view.findViewById(R.id.image)
        name = view.findViewById(R.id.name)
        phone = view.findViewById(R.id.phone)
        webSite = view.findViewById(R.id.webSite)
        addressMail = view.findViewById(R.id.mailAdress)
        aboutMe = view.findViewById(R.id.aboutMe)
        addNeighbor = view.findViewById(R.id.save_button)

        addNeighbor.setOnClickListener {

            // On récupère la taille de la liste des voisins pour incrémenter automatiquement l'ID
            val newNeighborId = NeighborRepository.getInstance().getNeighbours().size + 1

            val image: String = image.text.toString()
            val name: String = name.text.toString()
            val phone: String = phone.text.toString()
            val website: String = webSite.text.toString()
            val address: String = addressMail.text.toString()
            val aboutMe: String = aboutMe.text.toString()

            val newNeighbour = Neighbor(
                id = newNeighborId.toLong(),
                name = name,
                address = address,
                phoneNumber = phone,
                webSite = website,
                aboutMe = aboutMe,
                avatarUrl = image,
                favorite = false
            )
            NeighborRepository.getInstance().createNeighbour(newNeighbour)

            (activity as? NavigationListener)?.let {
                it.showFragment(ListNeighborsFragment())
            }
        }
        name.addTextChangedListener(this)
        addressMail.addTextChangedListener(this)
        phone.addTextChangedListener(this)
        webSite.addTextChangedListener(this)
        aboutMe.addTextChangedListener(this)
        image.addTextChangedListener(this)

        return view
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        validateData()
    }

    override fun afterTextChanged(p0: Editable?) {
    }

    private fun validateData() {

        val image_not_null: Boolean = !image.text.isNullOrEmpty()
        val name_not_null: Boolean = !name.text.isNullOrEmpty()
        val phone_not_null: Boolean = !phone.text.isNullOrEmpty()
        val website_not_null: Boolean = !webSite.text.isNullOrEmpty()
        val addressMai_not_null: Boolean = !addressMail.text.isNullOrEmpty()
        val aboutMe_not_null: Boolean = !aboutMe.text.isNullOrEmpty()

        val emailValid: Boolean = isValidEmail(addressMail.text)
        if (!emailValid && addressMai_not_null) {
            addressMail.error = getString(R.string.invalid_email)
        }

        val phoneNumberValid: Boolean = isValidPhoneNumber(phone.text)
        if (!phoneNumberValid && phone_not_null) {
            phone.setError("Format must be 0X XX XX XX XX")
        }

        val imageUrlValid: Boolean = isValidUrl(image.text)
        if (!imageUrlValid && image_not_null) {
            image.setError("Invalid image URL")
        }

        val websiteUrlValid: Boolean = isValidUrl(webSite.text)
        if (!websiteUrlValid && website_not_null) {
            webSite.setError("Invalid URL")
        }

        addNeighbor.isEnabled =
            image_not_null &&
            name_not_null &&
            phone_not_null &&
            website_not_null &&
            addressMai_not_null &&
            aboutMe_not_null &&
            emailValid &&
            phoneNumberValid &&
            imageUrlValid &&
            websiteUrlValid
    }

    fun isValidEmail(target: CharSequence?): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

    fun isValidPhoneNumber(target: CharSequence?): Boolean {
        return (
            (
                (target.toString()).startsWith("07") ||
                    (target.toString()).startsWith("06")
                ) &&
                target.toString().length == 10
            )
    }

    fun isValidUrl(target: CharSequence?): Boolean {
        return URLUtil.isValidUrl(target.toString())
    }
}
