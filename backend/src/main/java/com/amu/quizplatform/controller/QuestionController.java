package com.amu.quizplatform.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.amu.quizplatform.entity.Question;
import com.amu.quizplatform.service.QuestionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {

  private final QuestionService questionService;    

  @PostMapping
  public Question addQuestion(@RequestBody Question question)
  {
    return questionService.addQuestion(question);
  }
  @GetMapping
  public List<Question> getallQuestions()
  {
    return questionService.getallQuestions();
  }
  @GetMapping("/{id}")
  public Question getQuestionbyId(@PathVariable Long id)
  {
    return questionService.getQuestionbyId(id);
  }  
  @DeleteMapping("/{id}")
  public void deleteQuestionbyId(@PathVariable Long id)
  {
     questionService.deleteQuestionbyId(id);
  }  
  @PutMapping("/{id}")
  public Question updateQuestion(@PathVariable Long id,@RequestBody Question question)
  {
    return questionService.updateQuestion(id, question);
  }
}
