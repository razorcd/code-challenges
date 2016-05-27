class CutTheTree
  attr_reader :root

  def initialize vertices:, edges:
    nodes= []
    vertices.each_with_index do |v, i|
      nodes<< {
        id: i+1,
        value: v,
        links: []
      }
    end

    edges.each do |e|
      initial_link nodes[e[0]-1], nodes[e[1]-1]
    end

    @root= nodes[0]
    # nodes.map {|n| puts "val: #{n[:value]}, linked_values: #{n[:links].map {|nn| nn[:value]}.to_s}" }
    nodes= nil
  end

  def min_differance
    differences= []
    traverse(@root) do |node|
      node[:links].each do |n|
        next if n.nil?
        s1, s2= sums_after_cut(node, n)
        differences<< (s1-s2).abs
      end
    end
    differences.min
  end

  def sums_after_cut node1, node2
    unlink node1, node2
    sum1= sum(node1)
    sum2= sum(node2)
    link node1, node2
    return [sum1, sum2]
  end

  def sum start_node=nil
    sum= 0
    node= start_node || @root
    traverse(node) {|node| sum+= node[:value] }
    sum
  end

private

  def traverse start_node, &block
    _traverse(node: start_node, &block)
  end

  def _traverse node:, visited: [], &block
    visited<< node
    yield node

    node[:links].each do |n|
      next if n.nil?
      _traverse(node: n, visited: visited, &block) unless visited.include?(n)
    end
  end

  def initial_link node1, node2
    raise "initial_link called with nil nodes" if node1.nil? || node2.nil?
    node1[:links]<< node2
    node2[:links]<< node1
  end

  def link node1, node2
    raise "link called with nil nodes" if node1.nil? || node2.nil?
    i1= node1[:links].index {|e| e==nil}
    if i1
      node1[:links][i1]= node2
    end

    i2= node2[:links].index {|e| e==nil}
    if i2
      node2[:links][i2]= node1
    end
  end

  def unlink node1, node2
    raise "unlink called with nil nodes" if node1.nil? || node2.nil?
    i1= node1[:links].index {|e| e==node2}
    node1[:links][i1]= nil

    i2= node2[:links].index {|e| e==node1}
    node2[:links][i2]= nil
  end
end
