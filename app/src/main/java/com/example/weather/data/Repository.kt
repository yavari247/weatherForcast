package com.example.weather.data

import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject
@ActivityRetainedScoped

class Repository @Inject constructor(
     remoteDataSource: RemoteDataSource
) {
    val remote=remoteDataSource
}