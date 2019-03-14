package io.piano.service.result;

import io.piano.entity.Question;

import java.util.Collection;

/**
 * Created by maratgatin on 14/03/2019.
 */
public class QuestionServiceResult {

    private boolean hasMore;
    private Collection<Question> questions;

    public QuestionServiceResult(boolean hasMore, Collection<Question> questions) {
        this.hasMore = hasMore;
        this.questions = questions;
    }

    public boolean hasMore() {
        return hasMore;
    }

    public Collection<Question> getQuestions() {
        return questions;
    }
}
