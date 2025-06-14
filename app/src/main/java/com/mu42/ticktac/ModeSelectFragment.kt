package com.mu42.ticktac

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ModeSelectFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ModeSelectFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_mode_select, container, false)

        view.findViewById<CardView>(R.id.card2Player).setOnClickListener {
            startActivity(Intent(requireContext(), TwoPlayerActivity::class.java))
        }
        view.findViewById<CardView>(R.id.cardAI).setOnClickListener {
            startActivity(Intent(requireContext(), AiModeActivity::class.java))
        }

        return view
    }
}
