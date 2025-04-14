package com.sbs.java.board;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int lastArticleId = 0;
    Article lastArticle = null; //변수 초기화

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


        System.out.println("생성된 게시물 객체 : " + article);

        System.out.printf("%d번 게시물이 등록되었습니다.\n", id);

      }
      else if (cmd.equals("/usr/article/detail")) {
        Article article = lastArticle;

        //lastArticle는 null이 들어 있음 게시물을 등록을 안했는데 게시물 조회하면 오류남
        //그래서 유효성 검사가 필요함 (에러 보여주면 안 되기 때문에)
        if(article == null) {
          System.out.println("게시물이 존재하지 않습니다.");
          continue;
        }

        System.out.println("게시물 상세보기");
        System.out.printf("번호 : %d\n", article.id);
        System.out.printf("제목 : %s\n", article.subject);
        System.out.printf("내용 : %s\n", article.content);
      }
      else if (cmd.equals("exit")) {
        System.out.println("텍스트 게시판을 종료합니다.");
        break;
      }
      else {
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