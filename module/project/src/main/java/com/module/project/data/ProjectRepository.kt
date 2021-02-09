package com.module.project.data

import com.base.base.api.ApiUrl
import com.base.base.entity.remote.Article
import com.base.base.entity.remote.ArticleTree
import com.base.base.entity.remote.PageList
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.param.toApiResponse

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2021-02-01 9:48 AM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
object ProjectRepository {

    /**
     * 获取项目类型
     * @return ProjectTree
     */
    suspend fun getProjectTree(): ArrayList<ArticleTree> {
        return RxHttp.get(ApiUrl.Project.ProjectTree)
            .toApiResponse<ArrayList<ArticleTree>>()
            .await()
    }

    /**
     * 获取项目类型文章列表
     * @param page Int 页码
     * @param cid Int 类型id
     * @return ArticleList
     */
    suspend fun getProjectArticles(page: Int, cid: Int): PageList<Article> {
        return RxHttp.get(ApiUrl.Project.ProjectArticles, page, cid)
            .toApiResponse<PageList<Article>>()
            .await()
    }
}