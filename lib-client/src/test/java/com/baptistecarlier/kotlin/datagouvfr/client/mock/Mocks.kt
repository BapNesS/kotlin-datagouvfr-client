package com.baptistecarlier.kotlin.datagouvfr.client.mock

import com.baptistecarlier.kotlin.datagouvfr.client.annotation.MissingFieldMapping
import com.baptistecarlier.kotlin.datagouvfr.client.model.*
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

internal val mockString = ""

internal val mockBoolean = true

internal val mockBadge = Badge("")

@OptIn(MissingFieldMapping::class)
internal val mockCommunityResource = CommunityResource(
    FileTypeEnum.FILE, "", "", TypeEnum.API, ""
)

@OptIn(MissingFieldMapping::class)
internal val mockCommunityResourcePage = CommunityResourcePage(
    page = 0,
    pageSize = 0,
    total = 0
)
internal val mockDataset = Dataset(
    createdAt = Clock.System.now().toLocalDateTime(TimeZone.UTC),
    description = "",
    frequency = Dataset.FrequencyEnum.ANNUAL,
    lastModified = Clock.System.now().toLocalDateTime(TimeZone.UTC),
    lastUpdate = Clock.System.now().toLocalDateTime(TimeZone.UTC),
    page = "",
    slug = "",
    title = "",
    uri = ""
)

@OptIn(MissingFieldMapping::class)
internal val mockDatasetPage = DatasetPage(0, 0, 0)

@OptIn(MissingFieldMapping::class)
internal val mockDiscussion = Discussion("")

internal val mockDiscussionResponse = DiscussionResponse("")

internal val mockDiscussionSubject = DiscussionSubject(null, null, null)

@OptIn(MissingFieldMapping::class)
internal val mockDiscussionStart = DiscussionStart("", mockDiscussionSubject, "")

@OptIn(MissingFieldMapping::class)
internal val mockDiscussionPage = DiscussionPage(0, 0, 0)

@OptIn(MissingFieldMapping::class)
internal val mockFollowPage = FollowPage(0, 0, 0)

@OptIn(MissingFieldMapping::class)
internal val mockResource = Resource(
    fileType = FileTypeEnum.FILE,
    format = "",
    title = "",
    type = TypeEnum.API,
    url = ""
)

@OptIn(MissingFieldMapping::class)
internal val mockUploadedResource = UploadedResource(
    fileType = FileTypeEnum.FILE,
    format = "",
    title = "",
    type = TypeEnum.API,
    url = ""
)

@OptIn(MissingFieldMapping::class)
internal val mockHarvestSource = HarvestSource(
    active = true,
    autoarchive = true,
    backend = HarvestSource.BackendEnum.CKAN,
    createdAt = Clock.System.now().toLocalDateTime(TimeZone.UTC),
    name = "",
    url = ""
)

internal val mockHarvestBackend = HarvestBackend()

internal val mockHarvestJob = HarvestJob(
    created = Clock.System.now().toLocalDateTime(TimeZone.UTC),
    id = "",
    source = "",
    status = HarvestJobStatusEnum.DONE
)

@OptIn(MissingFieldMapping::class)
internal val mockHarvestJobPage = HarvestJobPage(0, 0, 0)

@OptIn(MissingFieldMapping::class)
internal val mockHarvestSourceValidation =
    HarvestSourceValidation(StatusEnum.ACCEPTED)

internal val mockHarvestJobPreview = HarvestJobPreview(
    created = Clock.System.now().toLocalDateTime(TimeZone.UTC),
    id = "",
    source = "",
    status = HarvestJobStatusEnum.DONE
)

@OptIn(MissingFieldMapping::class)
internal val mockOrganization = Organization(
    id = "",
    name = "",
    slug = ""
)

@OptIn(MissingFieldMapping::class)
internal val mockOrganizationPage = OrganizationPage(0, 0, 0)

internal val mockUploadedImage = UploadedImage()

internal val mockMember = Member(Member.RoleEnum.EDITOR)

internal val mockMembershipRequest = MembershipRequest(
    comment = "",
    status = StatusEnum.ACCEPTED
)

internal val mockRefuseMembership = RefuseMembership()

@OptIn(MissingFieldMapping::class)
internal val mockReusePage = ReusePage(0, 0, 0)

internal val mockReuse = Reuse("", "", TypeEnum.API, "")

internal val mockDatasetReference = DatasetReference("", "")

@OptIn(MissingFieldMapping::class)
internal val mockUserPage = UserPage(0, 0, 0)

@OptIn(MissingFieldMapping::class)
internal val mockUser = User(
    firstName = "",
    id = "",
    lastName = "",
    slug = "",
    uri = ""
)

@OptIn(MissingFieldMapping::class)
internal val mockPostPage = PostPage(0, 0, 0)

internal val mockPost = Post("", "", "")

internal val mockOembed = Oembed(0, "", 0, 0, "", "", 0)

internal val mockSite = Site("", "")

@OptIn(MissingFieldMapping::class)
internal val mockJob = Job("", Job.TaskEnum.HARVEST)

internal val mockTask = Task()

@OptIn(MissingFieldMapping::class)
internal val mockGeoJSONFeature = GeoJSONFeature(
    GeoJSON(GeoJSON.TypeEnum.LINESTRING),
    GeoJSONFeature.TypeEnum.FEATURE
)

internal val mockGeoJSONFeatureCollection = GeoJSONFeatureCollection(emptyList(), GeoJSONFeatureCollection.TypeEnum.FEATURECOLLECTION)

@OptIn(MissingFieldMapping::class)
internal val mockTopicPage = TopicPage(0, 0, 0)

internal val mockTopic = Topic("", "", emptyList())

internal val mockTransfer = Transfer()

internal val mockTransferResponse = TransferResponse(TransferResponse.ResponseEnum.ACCEPT)

internal val mockTransferRequest = TransferRequest("", TransferRequestRecipient(), TransferRequestSubject())

@OptIn(MissingFieldMapping::class)
internal val mockIssue = Issue("", "", mockUser)

@OptIn(MissingFieldMapping::class)
internal val mockIssuePage = IssuePage(0, 0, 0)

internal val mockIssueResponse = IssueResponse("", false)

internal val mockMe = Me(
    firstName = "",
    id = "",
    lastName = "",
    since = Clock.System.now().toLocalDateTime(TimeZone.UTC),
    slug = "",
    uri = ""
)

internal val mockApiKey = ApiKey("")
