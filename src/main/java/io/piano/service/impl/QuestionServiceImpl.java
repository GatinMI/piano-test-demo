package io.piano.service.impl;

import com.google.code.stackexchange.client.query.StackExchangeApiQueryFactory;
import com.google.code.stackexchange.common.PagedList;
import com.google.code.stackexchange.schema.Paging;
import io.piano.entity.Question;
import io.piano.service.QuestionService;
import io.piano.service.result.QuestionServiceResult;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by maratgatin on 14/03/2019.
 */

@Service
public class QuestionServiceImpl implements QuestionService{


    @Override
    public QuestionServiceResult getQuestionsInTitle(String title, int page) {
        StackExchangeApiQueryFactory queryFactory = StackExchangeApiQueryFactory.newInstance();
        PagedList<com.google.code.stackexchange.schema.Question> list = queryFactory.newSearchApiQuery()
                .withInTitle(title)
                .withPaging(new Paging(page, 30))
                .list();
        return new QuestionServiceResult(list.hasMore(), transformList(list));
    }

    private static List<Question> transformList(List<com.google.code.stackexchange.schema.Question> list) {
        return list.stream().map( e -> {
            Question q = new Question();
            q.setAnswered(e.getAcceptedAnswerId() != 0);
            q.setAuthor(e.getOwner().getDisplayName());
            q.setDate(e.getCreationDate());
            q.setLink(e.getQuestionUrl());
            q.setTitle(e.getTitle());
            return q;
        }).collect(Collectors.toList());
    }
}
