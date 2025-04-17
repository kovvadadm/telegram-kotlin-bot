package com.example.telegrambot

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.telegrambot.ui.theme.TelegramBotTheme
import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.entities.ChatId
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val telegramScope = CoroutineScope(Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startTelegramBot()

        setContent {
            TelegramBotTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    private fun startTelegramBot() {
        telegramScope.launch {
            try {
                val bot = bot {
                    token = "7819562891:AAHwFiYZxBnL25OTGIq_7Nv357_BOCEYnFM" // ⚠️ Не забувай замінити на свій токен

                    dispatch {
                        command("start") {
                            bot.sendMessage(
                                chatId = ChatId.fromId(message.chat.id),
                                text = "Привіт! Я ваш бот."
                            )
                        }
                    }
                }

                Log.i("TelegramBot", "Бот запускається...")
                bot.startPolling()
            } catch (e: Exception) {
                Log.e("TelegramBot", "Помилка запуску бота: ${e.message}", e)
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name! Перевірте бота в Telegram.",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TelegramBotTheme {
        Greeting("Android")
    }
}
