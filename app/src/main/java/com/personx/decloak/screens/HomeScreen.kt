package com.personx.decloak.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.personx.decloak.ClipboardManagerHelper
import com.personx.decloak.components.CyberpunkButton
import com.personx.decloak.components.CyberpunkInputBox
import com.personx.decloak.components.Header
import com.personx.decloak.components.PlaceholderInfo
import com.personx.decloak.ui.theme.DecloakTheme
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    windowSizeClass: WindowSizeClass,
    modifier: Modifier = Modifier
) {
    val clipboardManager = ClipboardManagerHelper(LocalContext.current)
    val scope = rememberCoroutineScope()

    Column(
    ) {
        Header(
            "INVESTIGATE EMAIL",
            windowSizeClass = windowSizeClass
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 16.dp)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.onSurface.copy(0.05f),
                            MaterialTheme.colorScheme.onPrimary.copy(0.01F)
                        )
                    )
                )
                .padding(24.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Input Box
            CyberpunkInputBox(
                value = "",
                onValueChange = { },
                placeholder = "Enter the email address",
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
            )

            // Detection Results
            if (false) {
                DetectionResultsSection(
                    detectedHashes = "Lore ipsum dolor sit amet".split(" "),
                    hashInfo = "Lorem ipsum dolor sit amet",
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            } else {
                PlaceholderInfo(
                    icon = Icons.Default.Fingerprint,
                    title = "Enter a email address to Decloak",
                    description = "lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
                )
            }

            // Copy Button (only shown when there's input)
            if (false) {
                CyberpunkButton(
                    onClick = {
                        scope.launch {
                            clipboardManager.copyText("")
                        }
                    },
                    icon = Icons.Default.ContentCopy,
                    text = "Copy Email"
                )
            }
        }
    }
}


@Composable
private fun DetectionResultsSection(
    detectedHashes: List<String>,
    hashInfo: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        // Detection Title
        Text(
            text = "lorem ipsum dolor sit amet",
            style = MaterialTheme.typography.titleLarge.copy(
                fontFamily = FontFamily.Monospace,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            ),
            modifier = Modifier.padding(vertical = 8.dp)
        )

        // Possible Algorithms
        Text(
            text = "Lorem ipsum dolor sit amet",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontFamily = FontFamily.Monospace,
                color = Color(0xFF00FFAA)
            ),
            modifier = Modifier.padding(top = 8.dp)
        )

        if (detectedHashes.isEmpty()) {
            Text(
                text = "Lorem ipsum",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontFamily = FontFamily.Monospace,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    fontStyle = FontStyle.Italic
                )
            )
        } else {
            Column {
                detectedHashes.forEach { hashType ->
                    Text(
                        text = "• $hashType",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontFamily = FontFamily.Monospace,
                            color = MaterialTheme.colorScheme.onSurface
                        ),
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }
        }

        // Detailed Information
        Text(
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontFamily = FontFamily.Monospace,
                color = Color(0xFF00FFAA)
            ),
            modifier = Modifier.padding(top = 16.dp)
        )

        Text(
            text = hashInfo,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontFamily = FontFamily.Monospace,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.9f)
            ),
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Preview
@Composable
fun HashDetectorPreview() {
    DecloakTheme(darkTheme = true) {
        // HashDetector()
    }
}