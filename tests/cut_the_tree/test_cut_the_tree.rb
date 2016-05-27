require 'test/unit'
require_relative '../../cut_the_tree/cut_the_tree'

class CutTheTreeTest < Test::Unit::TestCase
  def test_min_differance_1
    assert_equal(
      CutTheTree.new(
        vertices: [100, 200, 100, 500, 100, 600],
        edges: [[1,2], [2,3], [2,5], [4,5], [5,6]]
    ).min_differance, 400)
  end

  def test_min_differance_2
    c=CutTheTree.new(
      vertices: [205, 573, 985, 242, 830, 514, 592, 263, 142, 915],
      edges: [[2, 8], [10, 5], [1, 7], [6, 9], [4, 3], [8, 10], [5, 1], [7, 6], [9, 4]]
    )
    assert_equal(c.min_differance, 99)
  end

  def test_min_differance_3
    assert_equal(
      CutTheTree.new(
        vertices: [716, 365, 206, 641, 841, 585, 801, 645, 208, 924, 920, 286, 554, 832, 359, 836, 247, 959, 31, 322, 709, 860, 890, 195, 575, 905, 314, 41, 669, 549, 950, 736, 265, 507, 729, 457, 91, 529, 102, 650, 805, 373, 287, 710, 556, 645, 546, 154, 956, 928],
        edges: [[14, 25], [25, 13], [13, 20], [20, 24], [43, 2], [2, 48], [48, 42], [42, 5], [27, 18], [18, 30], [30, 7], [7, 36], [37, 9], [9, 23], [23, 49], [49, 15], [31, 26], [26, 29], [29, 50], [50, 21], [41, 45], [45, 10], [10, 17], [17, 34], [28, 47], [47, 44], [44, 11], [11, 16], [3, 8], [8, 39], [39, 38], [38, 22], [19, 32], [32, 12], [12, 40], [40, 46], [1, 35], [35, 4], [4, 33], [33, 6], [25, 2], [2, 27], [7, 37], [15, 50], [21, 10], [17, 28], [16, 39], [38, 19], [40, 1]]
    ).min_differance, 525)
  end

  def test_sums_after_cut_1
    c= CutTheTree.new(
      vertices: [100, 200, 100, 500, 100, 600],
      edges: [[1,2], [2,3], [2,5], [4,5], [5,6]]
    )
    n1= c.root
    n2= c.root[:links][0]
    assert_equal(c.sums_after_cut(n1,n2), [100, 1500])
  end

  def test_sums_after_cut_2
    c=CutTheTree.new(
      vertices: [205, 573, 985, 242, 830, 514, 592, 263, 142, 915],
      edges: [[2, 8], [10, 5], [1, 7], [6, 9], [4, 3], [8, 10], [5, 1], [7, 6], [9, 4]]
    )
    n1= c.root
    n2= c.root[:links][1]
    assert_equal(c.sums_after_cut(n1,n2), [2680, 2581])
  end

  def test_sum_1
    c= CutTheTree.new(
        vertices: [100, 200, 100, 500, 100, 600],
        edges: [[1,2], [2,3], [2,5], [4,5], [5,6]]
    )
    assert_equal(c.sum, 1600)
  end

  def test_sum_2
    c=CutTheTree.new(
      vertices: [205, 573, 985, 242, 830, 514, 592, 263, 142, 915],
      edges: [[2, 8], [10, 5], [1, 7], [6, 9], [4, 3], [8, 10], [5, 1], [7, 6], [9, 4]]
    )
    assert_equal(c.sum, 5261)
  end
end
