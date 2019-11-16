package com.youtubedl.data.repository

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.youtubedl.data.local.VideoLocalDataSource
import com.youtubedl.data.local.room.entity.VideoInfo
import com.youtubedl.data.remote.VideoRemoteDataSource
import io.reactivex.Flowable
import io.reactivex.Maybe
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Created by cuongpm on 1/14/19.
 */

class VideoRepositoryImplTest {

    private lateinit var localData: VideoLocalDataSource

    private lateinit var remoteData: VideoRemoteDataSource

    private lateinit var videoRepository: VideoRepositoryImpl

    private lateinit var videoInfo: VideoInfo

    private lateinit var videoInfo1: VideoInfo

    private lateinit var url: String

    @Before
    fun setup() {
        localData = mock()
        remoteData = mock()
        videoRepository = VideoRepositoryImpl(localData, remoteData)

        videoInfo = VideoInfo(title = "title", originalUrl = "originalUrl")
        videoInfo1 = VideoInfo(title = "title1", originalUrl = "originalUrl1")
        url = "videoUrl"
    }

    @Test
    fun `save video info into cache, local and remote source`() {
        videoRepository.saveVideoInfo(videoInfo)

        assertEquals(videoInfo, videoRepository.cachedVideos[videoInfo.originalUrl])
        verify(remoteData).saveVideoInfo(videoInfo)
        verify(localData).saveVideoInfo(videoInfo)
    }

    @Test
    fun `get video info from cache`() {
        videoRepository.cachedVideos[url] = videoInfo

        videoRepository.getVideoInfo(url).test()
            .assertNoErrors()
            .assertValue { it == videoInfo }
    }

    @Test
    fun `get video info from local source should save data to cache`() {
        doReturn(Flowable.just(videoInfo)).`when`(localData).getVideoInfo(url)
        doReturn(Flowable.just(videoInfo1)).`when`(remoteData).getVideoInfo(url)

        videoRepository.getVideoInfo(url).test()
            .assertNoErrors()
            .assertValue { it == videoInfo }

        assertEquals(videoInfo, videoRepository.cachedVideos[url])
    }

    @Test
    fun `get video info from remote source should save data to cache and local source`() {
        doReturn(
            Maybe.create<VideoInfo> { it.onComplete() }.toFlowable()
        ).`when`(localData).getVideoInfo(url)
        doReturn(Flowable.just(videoInfo)).`when`(remoteData).getVideoInfo(url)

        videoRepository.getVideoInfo(url).test()
            .assertNoErrors()
            .assertValue { it == videoInfo }

        assertEquals(videoInfo, videoRepository.cachedVideos[url])
        assertEquals(url, videoInfo.originalUrl)
        verify(localData).saveVideoInfo(videoInfo)
    }

}