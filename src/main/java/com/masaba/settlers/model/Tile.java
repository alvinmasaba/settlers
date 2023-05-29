package com.masaba.settlers.model;
import java.util.*;

public class Tile {
  private int row;
  private int index;
  private List<Tile> neighbours;
  private Map<String, Edge> edges;
  private Map<String, Vertex> vertices;
  private List<List<Tile>> board;


  public Tile(int row, int index, List<List<Tile>> board) {
    this.row = row;
    this.index = index;
    this.board = board;
    this.neighbours = findNeighbours();
    this.edges = findOrCreateEdges();
    this.vertices = findOrCreateVertices();
  }

  private List<Tile> findNeighbours() {
    // To do
    return new ArrayList<>();
  }

  private Map<String, Edge> findOrCreateEdges() {
    // To do
    return new HashMap<>();
  }

  private Map<String, Vertex> findOrCreateVertices() {
    // To do
    return new HashMap<>();
  }
}
