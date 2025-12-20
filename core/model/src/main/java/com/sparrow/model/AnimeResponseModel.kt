package com.sparrow.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class AnimeResponse(
    @SerialName("data") val data: List<AnimeData>,
    @SerialName("meta") val meta: Meta,
    @SerialName("links") val links: Links
)

@Serializable
data class AnimeData(
    @SerialName("id") val id: String,
    @SerialName("type") val type: String,
    @SerialName("links") val links: DataLinks,
    @SerialName("attributes") val attributes: Attributes,
    @SerialName("relationships") val relationships: Relationships? = null
)

@Serializable
data class Meta(
    @SerialName("count") val count: Int
)

@Serializable
data class Links(
    @SerialName("first") val first: String? = null,
    @SerialName("next") val next: String? = null,
    @SerialName("last") val last: String? = null
)

@Serializable
data class DataLinks(
    @SerialName("self") val self: String
)

@Serializable
data class Attributes(
    @SerialName("createdAt") val createdAt: String,
    @SerialName("updatedAt") val updatedAt: String,
    @SerialName("slug") val slug: String,
    @SerialName("synopsis") val synopsis: String? = null,
    @SerialName("description") val description: String? = null,
    @SerialName("coverImageTopOffset") val coverImageTopOffset: Int,
    @SerialName("titles") val titles: Titles,
    @SerialName("canonicalTitle") val canonicalTitle: String,
    @SerialName("abbreviatedTitles") val abbreviatedTitles: List<String>? = emptyList(),
    @SerialName("averageRating") val averageRating: String? = null,
    @SerialName("ratingFrequencies") val ratingFrequencies: Map<String, String>? = emptyMap(),
    @SerialName("userCount") val userCount: Int,
    @SerialName("favoritesCount") val favoritesCount: Int,
    @SerialName("startDate") val startDate: String? = null,
    @SerialName("endDate") val endDate: String? = null,
    @SerialName("nextRelease") val nextRelease: String? = null,
    @SerialName("popularityRank") val popularityRank: Int,
    @SerialName("ratingRank") val ratingRank: Int? = null,
    @SerialName("ageRating") val ageRating: String? = null,
    @SerialName("ageRatingGuide") val ageRatingGuide: String? = null,
    @SerialName("subtype") val subtype: String? = null,
    @SerialName("status") val status: String,
    @SerialName("tba") val tba: String? = null,
    @SerialName("posterImage") val posterImage: Image? = null,
    @SerialName("coverImage") val coverImage: Image? = null,
    @SerialName("episodeCount") val episodeCount: Int? = null,
    @SerialName("episodeLength") val episodeLength: Int? = null,
    @SerialName("totalLength") val totalLength: Int? = null,
    @SerialName("youtubeVideoId") val youtubeVideoId: String? = null,
    @SerialName("showType") val showType: String? = null,
    @SerialName("nsfw") val nsfw: Boolean? = false
)

@Serializable
data class Titles(
    @SerialName("en") val en: String? = null,
    @SerialName("en_jp") val enJp: String? = null,
    @SerialName("ja_jp") val jaJp: String? = null,
    @SerialName("en_us") val enUs: String? = null
)

@Serializable
data class Image(
    @SerialName("tiny") val tiny: String? = null,
    @SerialName("small") val small: String? = null,
    @SerialName("medium") val medium: String? = null,
    @SerialName("large") val large: String? = null,
    @SerialName("original") val original: String? = null,
    @SerialName("meta") val meta: ImageMeta? = null
)

@Serializable
data class ImageMeta(
    @SerialName("dimensions") val dimensions: ImageDimensions? = null
)

@Serializable
data class ImageDimensions(
    @SerialName("tiny") val tiny: ImageDimension? = null,
    @SerialName("small") val small: ImageDimension? = null,
    @SerialName("medium") val medium: ImageDimension? = null,
    @SerialName("large") val large: ImageDimension? = null
)

@Serializable
data class ImageDimension(
    @SerialName("width") val width: Int? = null,
    @SerialName("height") val height: Int? = null
)

@Serializable
data class Relationships(
    @SerialName("genres") val genres: RelationshipLinks? = null,
    @SerialName("categories") val categories: RelationshipLinks? = null,
    @SerialName("castings") val castings: RelationshipLinks? = null,
    @SerialName("installments") val installments: RelationshipLinks? = null,
    @SerialName("mappings") val mappings: RelationshipLinks? = null,
    @SerialName("reviews") val reviews: RelationshipLinks? = null,
    @SerialName("mediaRelationships") val mediaRelationships: RelationshipLinks? = null,
    @SerialName("characters") val characters: RelationshipLinks? = null,
    @SerialName("staff") val staff: RelationshipLinks? = null,
    @SerialName("productions") val productions: RelationshipLinks? = null,
    @SerialName("quotes") val quotes: RelationshipLinks? = null,
    @SerialName("episodes") val episodes: RelationshipLinks? = null,
    @SerialName("streamingLinks") val streamingLinks: RelationshipLinks? = null,
    @SerialName("animeProductions") val animeProductions: RelationshipLinks? = null,
    @SerialName("animeCharacters") val animeCharacters: RelationshipLinks? = null,
    @SerialName("animeStaff") val animeStaff: RelationshipLinks? = null
)

@Serializable
data class RelationshipLinks(
    @SerialName("links") val links: RelationshipLink
)

@Serializable
data class RelationshipLink(
    @SerialName("self") val self: String,
    @SerialName("related") val related: String
)