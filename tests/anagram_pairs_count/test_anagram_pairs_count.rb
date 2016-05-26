require 'test/unit'
require_relative '../../anagram_pairs_count/anagram_pairs_count'

class AnagramPairsTest < Test::Unit::TestCase
  def test_case_1
    assert_equal(AnagramPairs.new("ifailuhkqq").count, 3)
  end

  def test_case_2
    assert_equal(AnagramPairs.new("hucpoltgty").count, 2)
  end

  def test_case_3
    assert_equal(AnagramPairs.new("pvmupwjjjf").count, 6)
  end

  def test_case_4
    assert_equal(AnagramPairs.new("iwwhrlkpek").count, 3)
  end

  def test_case_5
    assert_equal(AnagramPairs.new("ifailuhkqq").count, 3)
  end

  def test_case_6
    assert_equal(AnagramPairs.new("abba").count, 4)
  end

  def test_case_7
    assert_equal(AnagramPairs.new("bbcaadacaacbdddcdbddaddabcccdaaadcadcbddadababdaaabcccdcdaacadcababbabbdbacabbdcbbbbbddacdbbcdddbaaa").count, 4832)
  end

  def test_case_8
    assert_equal(AnagramPairs.new("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa").count, 166650)
  end
end
