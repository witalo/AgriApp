package com.example.agriapp.data.remote

import com.apollographql.apollo3.ApolloClient
import com.example.agriapp.util.Constants

object GraphQLClient {
    private const val BASE_URL = Constants.GRAPHQL_URL

    val apolloClient: ApolloClient = ApolloClient.Builder()
        .serverUrl(BASE_URL)
        .build()
}
