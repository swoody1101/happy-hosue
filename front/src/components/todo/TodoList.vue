<template>
  <b-container class="bv-example-row">
    <b-row class="mt-4 mb-4 text-center">
      <b-col>
        <todo-list-item
          v-for="(todo, index) in todos"
          :key="index"
          :todo="todo"
        />
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState } from "vuex";
import TodoListItem from "@/components/todo/TodoListItem";

const todoStore = "todoStore";

export default {
  name: "TodoList",
  components: {
    TodoListItem,
  },
  computed: {
    //   1.
    // todos() {
    //   return this.$store.state.todos;
    // },
    // 2.
    // ...mapState({
    //   todos: 'todos',
    // }),
    // 3.
    ...mapState(todoStore, ["todos"]),
  },
};
</script>

<style></style>
