package ru.rpuxa.aviasalesapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ru.aviasales.core.AviasalesSDK
import ru.aviasales.core.identification.SdkConfig
import ru.aviasales.template.ui.fragment.AviasalesFragment
import ru.aviasales.template.utils.PrivacyPolicyUrl

class MainActivity : AppCompatActivity() {
    private var aviasalesFragment: AviasalesFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Replace with your Privacy policy URL or leave blank for default Privacy policy
        PrivacyPolicyUrl.setUrl("")

        AviasalesSDK.getInstance()
            .init(this, SdkConfig(TRAVEL_PAYOUTS_MARKER, TRAVEL_PAYOUTS_TOKEN, SDK_HOST))
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        initFragment()
    }

    private fun initFragment() {
        val fm = supportFragmentManager
        aviasalesFragment = fm.findFragmentByTag(AviasalesFragment.TAG) as AviasalesFragment?

        if (aviasalesFragment == null) {
            aviasalesFragment = AviasalesFragment.newInstance() as AviasalesFragment
        }

        val fragmentTransaction = fm.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_place, aviasalesFragment!!, AviasalesFragment.TAG)
        fragmentTransaction.commit()
    }

    override fun onBackPressed() {
        if (!aviasalesFragment!!.onBackPressed()) {
            super.onBackPressed()
        }
    }

    companion object {

        // replace with your travel payout credentials
        private const val TRAVEL_PAYOUTS_MARKER = "167942"
        private const val TRAVEL_PAYOUTS_TOKEN = "4a6b9acb55d8aaa9a9c99e8666187638"
        private const val SDK_HOST = "avia.putihod.ru/"
    }
}
