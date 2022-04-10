package com.news.newsspringboot.service.impl;

import com.news.newsspringboot.model.entity.User;
import com.news.newsspringboot.model.entity.article.Article;
import com.news.newsspringboot.model.entity.article.ArticleLike;
import com.news.newsspringboot.model.entity.article.ArticleStar;
import com.news.newsspringboot.model.entity.article.ArticleComment;
import com.news.newsspringboot.model.mapper.ArticleCommentMapper;
import com.news.newsspringboot.model.mapper.ArticleMapper;
import com.news.newsspringboot.model.vo.ArticleCommentVo;
import com.news.newsspringboot.model.vo.ArticleLikePreview;
import com.news.newsspringboot.model.vo.ArticlePreview;
import com.news.newsspringboot.model.vo.ArticleVo;
import com.news.newsspringboot.repository.*;
import com.news.newsspringboot.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ArticleServiceImpl implements ArticleService {
    ArticleRepository repository;
    ArticleMapper articleMapper;
    ArticleLikeRepository articleLikeRepository;
    ArticleStarRepository articleStarRepository;
    ArticleCommentRepository articleCommentRepository;
    ArticleCommentMapper articleCommentMapper;
    UserRepository userRepository;

    @Override
    public Article publish(Article article) {
        return repository.save(article);
    }

    @Override
    public void delete(String articleId) {
        repository.deleteById(articleId);
    }

    @Override
    public int editContent(String articleId, String content){
        Article preArticle = repository.getById(articleId);
        preArticle.setContent(content);
        return 0;
    }

    @Override
    public Page<Article> search(String titleLike, Pageable pageable) {
        return repository.findByTitleLike(titleLike, pageable);
    }

    @Override
    public List<ArticlePreview> getAllByType(String typeId) {
        List<Article> list;
        List<ArticlePreview> listPreview = new ArrayList<>();

        list = repository.findAllByTypeId(typeId);
        for(Article article : list){
            listPreview.add(articleMapper.toPreview(article));
        }
        return listPreview;
    }

    @Override
    public List<ArticleLikePreview> getUserLike(String userId) {
        List<ArticleLike> articleLikes = articleLikeRepository.findAllByUserid(userId);
        List<ArticleLikePreview> res=new ArrayList<>();
        for (ArticleLike al:articleLikes) {
            Article article = repository.getById(al.getArticleid());
            res.add(articleMapper.toLikePreview(article, al.getLiketime()));
        }
        return res;
    }

    @Override
    public List<ArticleLikePreview> getUserStar(String userId) {
        List<ArticleStar> articleStars = articleStarRepository.findAllByUseridOrderByLiketimeDesc(userId);
        List<ArticleLikePreview> res=new ArrayList<>();
        for (ArticleStar al:articleStars) {
            Article article = repository.getById(al.getArticleid());
            res.add(articleMapper.toLikePreview(article, al.getLiketime()));
        }
        return res;
    }

    @Override
    public List<ArticleCommentVo> getAllComments(String articleId) {
        List<ArticleComment> articleComments = articleCommentRepository.findByArticleidOrderByCommentlikeDesc(articleId);
        List<ArticleCommentVo> articleCommentVos = new ArrayList<>();
        for(ArticleComment articleComment: articleComments){
            articleCommentVos.add(articleCommentMapper.toVo(articleComment));
        }
        return  articleCommentVos;
    }

    @Override
    public ArticleVo getArticle(String articleId) {
        return articleMapper.toVo(repository.getById(articleId));
    }

    @Override
    public List<ArticleVo> getReconmendArticles(String userId) {

        List<Article> articles = recommendArticle(userId);
        List<ArticleVo> articleVos = new ArrayList<>();
        for(Article article:articles){
            articleVos.add(articleMapper.toVo(article));
        }
        return articleVos;
    }

    @Autowired
    public void setRepository(ArticleRepository repository) {
        this.repository = repository;
    }
    @Autowired
    public void setArticleMapper(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }
    @Autowired
    public void setArticleLikeRepository(ArticleLikeRepository articleLikeRepository) {
        this.articleLikeRepository = articleLikeRepository;
    }
    @Autowired
    public void setArticleStarRepository(ArticleStarRepository articleStarRepository) {
        this.articleStarRepository = articleStarRepository;
    }
    @Autowired
    public void setArticleCommentRepository(ArticleCommentRepository articleCommentRepository) {
        this.articleCommentRepository = articleCommentRepository;
    }
    @Autowired
    public void setArticleCommentMapper(ArticleCommentMapper articleCommentMapper) {
        this.articleCommentMapper = articleCommentMapper;
    }
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private List<Article> recommendArticle(String userid){
        List<ArticleLike> likeLists;
        List<ArticleStar> stars;
        List<User> users = userRepository.findAll(); //所有用户列表
        List<Article> papers = repository.findAll();//所有论文列表
        Integer[][] curMatrix = new Integer[papers.size()+5][papers.size()+5];   //当前矩阵
        Integer[][] comMatrix = new Integer[papers.size()+5][papers.size()+5];   //共现矩阵
        Integer[] N = new Integer[papers.size()+5]; //喜欢每个物品的人数

        Arrays.fill(N, 0);
        for(int i = 0; i < papers.size(); i++)
            for(int j = 0; j < papers.size(); j++)
                comMatrix[i][j] = 0;

        Map<String, Integer> map = new HashMap<String, Integer>();    //文章id:paper表索引

        for(int i=0; i<papers.size(); i++){
            map.put(papers.get(i).getId(), i);
        }


        for(User user: users){
            if(Objects.equals(user.getId(), userid)) continue;                    //当前用户则跳过

            likeLists = articleLikeRepository.findAllByUserid(userid);     //当前用户的喜欢列表
            stars = articleStarRepository.findAllByUseridOrderByLiketimeDesc(userid);

            for(int i = 0; i < papers.size(); i++)
                for(int j = 0; j < papers.size(); j++)
                    curMatrix[i][j] = 0;                               //清空矩阵

            for(int i = 0; i < likeLists.size(); i++){
                String articleid = likeLists.get(i).getArticleid();
                Integer pid1 = map.get(articleid);
                ++N[pid1];
                for(int j = i+1; j < likeLists.size(); j++){
                    String articleid2 = likeLists.get(j).getArticleid();
                    int pid2 = map.get(articleid2);
                    ++curMatrix[pid1][pid2];
                    ++curMatrix[pid2][pid1]; //两两加一
                }
            }

            for(int i = 0; i < stars.size(); i++){
                String articleid = stars.get(i).getArticleid();
                Integer pid1 = map.get(articleid);
                ++N[pid1];
                for(int j = i+1; j < stars.size(); j++){
                    String articleid2 = stars.get(j).getArticleid();
                    int pid2 = map.get(articleid2);
                    ++curMatrix[pid1][pid2];
                    ++curMatrix[pid2][pid1]; //两两加一
                }
            }

            //累加所有矩阵, 得到共现矩阵
            for(int i = 0; i < papers.size(); i++){
                for (Article paper : papers) {
                    String article1 = papers.get(i).getId(), article2 = paper.getId();
                    int pid1 = map.get(article1), pid2 = map.get(article2);
                    comMatrix[pid1][pid2] += curMatrix[pid1][pid2];
                    comMatrix[pid1][pid2] += curMatrix[pid1][pid2];
                }
            }
        }


        TreeSet<Article> preList = new TreeSet<Article>(new Comparator<Article>() {
            @Override
            public int compare(Article o1, Article o2) {
                if(o1.getW()!=o2.getW())
                    return (int) (o1.getW()-o2.getW())*100;
                else
                    return o1.getArticle_like()-o2.getArticle_like();
            }
        }); //预处理的列表

        likeLists = articleLikeRepository.findAllByUserid(userid);       //当前用户喜欢的论文列表
        boolean[] used = new boolean[papers.size()+5];  //判重数组
        for(ArticleLike like: likeLists){
            int Nij = 0;                         //既喜欢i又喜欢j的人数
            double Wij;                          //相似度
            Article tmp;                           //当前的论文

            String articleid = like.getArticleid();
            int i = map.get(articleid);
            for(Article paper: papers){
                if(Objects.equals(like.getArticleid(), paper.getId())) continue;
                int j = map.get(paper.getId());

                Nij = comMatrix[i][j];
                Wij = (double)Nij/Math.sqrt(N[i]*N[j]); //计算余弦相似度

                tmp = repository.getById(paper.getId());

                tmp.setW(Wij);


                if(used[map.get(tmp.getId())]) continue;
                preList.add(tmp);
                used[map.get(tmp.getId())] = true;
            }
        }

        List<Article> recomLists = new ArrayList<>();      //生成的推荐结果
        for(int i = 0; preList.size()>0 && i<5; i++){
            recomLists.add(preList.pollLast());
            preList.pollLast();
        }
        if(recomLists.size()<5){
            recomLists.addAll(preList);
            //推荐数量不满5个, 补足喜欢数最高的文章
            //recomLists .addAll(repository.findAllOrderByArticleLike());
        }

        return recomLists;

    }

}
