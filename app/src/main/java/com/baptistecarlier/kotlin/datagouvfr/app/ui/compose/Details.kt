package com.baptistecarlier.kotlin.datagouvfr.app.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.baptistecarlier.kotlin.datagouvfr.app.R
import com.baptistecarlier.kotlin.datagouvfr.app.theme.*
import com.baptistecarlier.kotlin.datagouvfr.client.model.Dataset
import com.baptistecarlier.kotlin.datagouvfr.extensions.date
import com.baptistecarlier.kotlin.datagouvfr.extensions.getAuthor
import com.baptistecarlier.kotlin.datagouvfr.extensions.getTagsOrNull


@Composable
fun DetailsView(dataset: Dataset?) {
    if (dataset != null) {
        DetailsLoaded(dataset)
    } else {
        DetailsLoader()
    }
}

@Composable
private fun DetailsLoader() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(52.dp)
            // Workaournd for Compose+XML
            .background(Color.Transparent)
    ) {
        val squareSize = 60.dp
        CircularProgressIndicator(
            color = MaterialTheme.colors.secondary,
            modifier = Modifier
                .width(squareSize)
                .height(squareSize)
        )
    }
}

@Composable
private fun DetailsLoaded(dataset: Dataset) {
    val horizontalGuideline = 24.dp
    val verticalGuideline = 16.dp
    val verticalPaddingModifier = Modifier.padding(vertical = verticalGuideline)
    Surface(
        color = Color.Transparent,
        contentColor = contentColorFor(MaterialTheme.colors.background),
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = dataset.title,
                style = MaterialTheme.typography.h6,
                modifier = verticalPaddingModifier
                    .padding(horizontal = horizontalGuideline)
            )

            DatasetStats(
                dataset,
                modifier = Modifier
                    .padding(horizontal = horizontalGuideline)
                    .padding(bottom = verticalGuideline)
            )

            OdfFoutainTheme(isSystemInDarkTheme()) {
                Surface(modifier = verticalPaddingModifier) {
                    Column(modifier = verticalPaddingModifier.fillMaxWidth()) {
                        Text(
                            text = dataset.getAuthor(),
                            style = MaterialTheme.typography.subtitle1,
                            modifier = Modifier
                                .padding(
                                    horizontal = horizontalGuideline
                                )
                                .padding(bottom = verticalGuideline/2)
                        )
                        Text(
                            text = stringResource(R.string.id_formatted, dataset.id.toString()),
                            style = MaterialTheme.typography.body2,
                            modifier = Modifier
                                .padding(horizontal = horizontalGuideline)
                        )
                        Text(
                            text = stringResource(
                                R.string.create_date_formatted,
                                listOf(dataset.createdAt.date())
                            ),
                            style = MaterialTheme.typography.body2,
                            modifier = Modifier
                                .padding(horizontal = horizontalGuideline)
                        )
                        Text(
                            text = stringResource(
                                R.string.update_date_formatted,
                                dataset.lastUpdate.date()
                            ),
                            style = MaterialTheme.typography.body2,
                            modifier = Modifier
                                .padding(horizontal = horizontalGuideline)
                        )
                        val tagsValue = dataset.getTagsOrNull()
                        val tagsContent = if (tagsValue != null) {
                            stringResource(R.string.tags_formatted, tagsValue)
                        } else {
                            stringResource(R.string.tags_formatted, stringResource(R.string.empty))
                        }
                        Text(
                            text = tagsContent,
                            style = MaterialTheme.typography.body2,
                            modifier = Modifier
                                .padding(horizontal = horizontalGuideline)
                                .padding(top = verticalGuideline/2)
                        )
                    }
                }
            }

            Text(
                text = dataset.description,
                style = MaterialTheme.typography.body2,
                modifier = verticalPaddingModifier
                    .padding(horizontal = horizontalGuideline)
            )
        }
    }
}

@Composable
fun DatasetStats(
    dataset: Dataset,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        val spacerWidth = Modifier.width(20.dp)
        val iconSize = 24.dp
        val iconHorizontalPadding = Modifier.width(4.dp)


        val followers = dataset.metrics?.followers ?: 0
        if (followers > 0) {
            Icon(
                painter = painterResource(id = R.drawable.ic_star_rate),
                tint = MaterialTheme.colors.odfFollowers,
                contentDescription = stringResource(R.string.followers),
                modifier = Modifier
                    .width(iconSize)
                    .height(iconSize)
            )
            Spacer(modifier = iconHorizontalPadding)
            Text(
                text = stringResource(
                    R.string.followers,
                    followers
                ),
                color = MaterialTheme.colors.odfTextSecondary,
                style = MaterialTheme.typography.odfTypeInfos
            )
            Spacer(modifier = spacerWidth)
        }

        val reuses = dataset.metrics?.reuses ?: 0
        if (reuses > 0) {
            Icon(
                painter = painterResource(id = R.drawable.ic_swap_calls),
                tint = MaterialTheme.colors.odfReuses,
                contentDescription = stringResource(R.string.reuses),
                modifier = Modifier
                    .width(iconSize)
                    .height(iconSize)
            )
            Spacer(modifier = iconHorizontalPadding)
            Text(
                text = stringResource(
                    R.string.reuses,
                    reuses
                ),
                color = MaterialTheme.colors.odfTextSecondary,
                style = MaterialTheme.typography.odfTypeInfos
            )
            Spacer(modifier = spacerWidth)
        }

        val ressources = dataset.resources?.size ?: 0
        if (ressources > 0) {
            Icon(
                painter = painterResource(id = R.drawable.ic_file_copy),
                tint = MaterialTheme.colors.odfResources,
                contentDescription = stringResource(R.string.resources),
                modifier = Modifier
                    .width(iconSize)
                    .height(iconSize)
            )
            Spacer(modifier = iconHorizontalPadding)
            Text(
                text = stringResource(
                    R.string.resources,
                    ressources
                ),
                color = MaterialTheme.colors.odfTextSecondary,
                style = MaterialTheme.typography.odfTypeInfos
            )
        }
    }
}