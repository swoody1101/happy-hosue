<template>
  <b-container class="bv-example-row">
    <b-row class="mt-1 text-center">
      <b-col> <h1>Todo it!</h1> </b-col>
    </b-row>
    <b-row class="mt-1 text-center">
      <b-col>
        <b-alert show variant="primary">할일 : {{ allTodosCount }}</b-alert>
      </b-col>
      <b-col>
        <b-alert show variant="success"
          >진행 : {{ unCompletedTodosCount }}</b-alert
        >
      </b-col>
      <b-col>
        <b-alert show variant="danger"
          >완료 : {{ completedTodosCount }}</b-alert
        >
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapGetters } from "vuex";

const todoStore = "todoStore";

export default {
  name: "TodoHeader",
  computed: {
    // 1.
    // allTodosCount() {
    //   return this.$store.getters.allTodosCount;
    // },
    // completedTodosCount() {
    //   return this.$store.getters.completedTodosCount;
    // },
    // unCompletedTodosCount() {
    //   return this.$store.getters.unCompletedTodosCount;
    // },
    // 2.
    // ...mapGetters({
    //   allTodosCount: 'allTodosCount',
    //   completedTodosCount: 'completedTodosCount',
    //   unCompletedTodosCount: 'unCompletedTodosCount',
    // }),
    // 3.
    ...mapGetters(todoStore, [
      "allTodosCount",
      "completedTodosCount",
      "unCompletedTodosCount",
    ]),
  },
};
</script>

<style></style>
