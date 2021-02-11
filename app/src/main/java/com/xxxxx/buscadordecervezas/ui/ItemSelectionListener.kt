package com.xxxxx.buscadordecervezas.ui

import com.xxxxx.buscadordecervezas.data.Beer

interface ItemSelectionListener {
    fun onItemSelected(beer: Beer)
}