package com.example.wikitest.view.exaption

import java.io.IOException

class NoConnectivityException(message: String = "No internet connectivity!") :
    IOException(message)