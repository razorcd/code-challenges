require 'test/unit'
require_relative '../../english_numbers_kata/english_numbers_kata'

class EnglishNumbersKataTest < Test::Unit::TestCase
  def test_initialize
    assert_equal(defined?(EnglishNumbers.new(37545)).!.!, true)
  end

  def test_case_1
    assert_equal(EnglishNumbers.new(3).to_english, "three")
  end

  def test_case_2
    assert_equal(EnglishNumbers.new(45).to_english, "forty-five")
  end
end
