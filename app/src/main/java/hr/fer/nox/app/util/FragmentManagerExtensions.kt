package hr.fer.nox.app.util

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) = beginTransaction().func().commit()

inline fun FragmentManager.inTransactionAndAddToBackStack(func: FragmentTransaction.() -> FragmentTransaction) = beginTransaction().func().addToBackStack(null).commit()