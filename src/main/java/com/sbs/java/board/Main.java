package com.sbs.java.board;

import javax.swing.plaf.IconUIResource;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {

  static void makeArticleTestData(List<Article> articles) { //static안에서 만들어진 함수는 static으로 만들어야됨
    // List<Article> articles = new ArrayList<>(); -- 데이터 보존 안 됨, articles는 main에서 만든걸 전달
    /*
    articles.add(new Article(1, "제목1", "내용1"));
    articles.add(new Article(1, "제목2", "내용2"));
    articles.add(new Article(1, "제목3", "내용3"));
     */
    // 반복문 - for
    /*
    for (int i = 1; i <= 3; i++) {
      articles.add(new Article(i, "제목" + i, "내용" + i);
    }
     */
    // 반복문 - stream 문법

    IntStream.rangeClosed(1, 3)
        .forEach(i -> articles.add(new Article(i, "제목" + i, "내용" + i)));

  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    List<Article> articles = new ArrayList<>(); //주소값 연결됨 -- toString 때문에 값들어옴

    int lastArticleId = 0;
    Article lastArticle = null; //변수 초기화

    makeArticleTestData(articles);


    System.out.println("== 자바 텍스트 게시판 ==");
    System.out.println("텍스트 게시판을 시작합니다.");

    while (true) {
      System.out.print("명령) ");
      String cmd = sc.nextLine();

      if (cmd.equals("/usr/article/write")) {
        System.out.println("== 게시물 작성 ==");
        System.out.print("제목 : ");
        String subject = sc.nextLine();

        if (subject.trim().isEmpty()) {
          System.out.println("제목을 입력해주세요.");
          continue;
        }

        System.out.print("내용 : ");
        String content = sc.nextLine();
        if (subject.trim().isEmpty()) {
          System.out.println("내용을 입력해주세요.");
          continue;
        }

        int id = ++lastArticleId;

        //객체 생성 후, 객체가 가지고 잇는 변수에 데이터 저장
        Article article = new Article(id, subject, content);
        lastArticle = article; //해당 객체 주소값을 공유함

        articles.add(article);

        System.out.printf("%d번 게시물이 등록되었습니다.\n", id);

      }
      //현재 데이터 유지가 안 됨 --> list에 저장
      else if (cmd.equals("/usr/article/list")) {

        //배열은 length, 리스트는 size
        if (articles.isEmpty()) {
          System.out.println("게시물이 존재하지 않습니다.");
          continue;
        }

        System.out.println("== 게시물 리스트 ==");
        System.out.println("번호 | 제목");

        //v1
        /*
        for (int i = 0; i < articles.size(); i++) {
          Article article = articles.get(i);
          System.out.printf("%d | %s",article.id, article.subject);
        }
         */
        /*
        //v2 - 향상된 for문
        for (Article article : articles) {
          System.out.printf("%d | %s",article.id, article.subject);
        }
         */

        //v3 - forEach문
        articles.forEach(article
            -> System.out.printf("%d | %s", article.id, article.subject));

        System.out.println(articles);
      } else if (cmd.equals("/usr/article/detail")) {
        Article article = lastArticle;

        //lastArticle는 null이 들어 있음 게시물을 등록을 안했는데 게시물 조회하면 오류남
        //그래서 유효성 검사가 필요함 (에러 보여주면 안 되기 때문에)
        if (article == null) {
          System.out.println("게시물이 존재하지 않습니다.");
          continue;
        }

        System.out.println("게시물 상세보기");
        System.out.printf("번호 : %d\n", article.id);
        System.out.printf("제목 : %s\n", article.subject);
        System.out.printf("내용 : %s\n", article.content);
      } else if (cmd.equals("exit")) {
        System.out.println("텍스트 게시판을 종료합니다.");
        break;
      } else {
        System.out.println("잘못 입력 된 명령어입니다");
      }
    }


    System.out.println("== 자바 텍스트 게시판 종료 ==");
    sc.close();

  }
}

class Article {
  int id;
  String subject;
  String content;

  Article(int id, String subject, String content) {
    this.id = id;
    this.subject = subject;
    this.content = content;
  }

  @Override //어노텐션-메서드 오버라이딩 한거라는 뜻
  //toString 메서드를 쓴 건 개발자가 디버깅을 했을 때 어떤 데이터가 들어있는지 확인을 위함임 프로그램성능 좋아지는게 ㄴㄴ
  public String toString() {
    return "{id: %d, subject: \"%s\", content: \"%s\"}".formatted(id, subject, content);
  }
}