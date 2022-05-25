<template>
  <div>
    <h3>게시물 상세 페이지</h3>
    <div>
      <button
        @click="moveModifyArticle"
        v-if="article.bwriter == userInfo.userid"
      >
        수정
      </button>
      <button
        @click="deleteArticle"
        v-if="
          article.bwriter == userInfo.userid || userInfo.role == 'ROLE_ADMIN'
        "
      >
        삭제
      </button>
    </div>
    <div>
      <ul>
        <li>번호 : {{ article.bno }}</li>
        <li>제목 : {{ article.btitle }}</li>
        <li>작성자 : {{ article.bwriter }}</li>
        <li>내용 : {{ article.bcontent }}</li>
        <li>작성일 : {{ article.bregtime }}</li>
        <li>조회: {{ article.breadCount + 1 }}</li>
      </ul>
    </div>

    <comment-list :bno="bno"></comment-list>
  </div>
</template>

<script>
import CommentList from "@/components/board/comment/CommentList.vue";
import { getArticle, deleteArticle, readCountUpArticle } from "@/api/board";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "BoardDetail",
  components: { CommentList },
  data() {
    return {
      bno: 0,
      article: {},
    };
  },
  created() {
    this.bno = this.$route.params.articleno;
    this.breadCountUp();
    getArticle(
      this.bno,
      (response) => {
        this.article = response.data;
      },
      (error) => {
        console.log("삭제시 에러발생!!", error);
      },
    );
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    moveModifyArticle() {
      this.$router.replace({
        name: "boardModify",
        params: { articleno: this.article.bno },
      });
      console.log(this.article.bno);
    },
    deleteArticle() {
      if (confirm("정말로 삭제?")) {
        deleteArticle(this.article.bno, () => {
          this.$router.push({ name: "boardList" }),
            (error) => {
              console.log(error);
            };
        });
      }
    },
    breadCountUp() {
      console.log(this.breadCount);
      readCountUpArticle(
        this.bno,
        () => {},
        (error) => {
          console.log(error);
        },
      );
      console.log(this.breadCount);
    },
  },
};
</script>

<style></style>
