package io.piano.service;
import io.piano.service.result.QuestionServiceResult;

/**
 * Created by maratgatin on 14/03/2019.
 */

public interface QuestionService {

    QuestionServiceResult getQuestionsInTitle(String title, int page);

}
