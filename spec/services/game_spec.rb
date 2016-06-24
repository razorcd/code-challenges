require "rails_helper"

RSpec.describe Game do
  let(:game) { Game.new }
  context "new game" do
    it "should have a score of 0" do
      expect(game.score).to eq 0
    end

    it "should have 10 total frames" do
      expect(Game::TOTOAL_FRAMES_COUNT).to eq 10
    end
  end

  context "one frame" do
    it "should have correct score" do
      game.throw! knocked_pins: 5
      expect(game.score).to eq 5

      game.throw! knocked_pins: 3
      expect(game.score).to eq 8
    end
  end

  context "multiple frames" do
    it "should have correct score" do
      game.throw! knocked_pins: 5
      game.throw! knocked_pins: 3

      game.throw! knocked_pins: 1
      game.throw! knocked_pins: 2

      expect(game.score).to eq 11
    end

    context "with a strike" do
      it "should have correct score" do
        game.throw! knocked_pins: 10

        expect(game.score).to eq 10

        game.throw! knocked_pins: 5
        game.throw! knocked_pins: 3

        expect(game.score).to eq 26
      end
    end

    context "with a spare" do
      it "should have correct score" do
        game.throw! knocked_pins: 4
        game.throw! knocked_pins: 6

        expect(game.score).to eq 10

        game.throw! knocked_pins: 5
        game.throw! knocked_pins: 3

        expect(game.score).to eq 23
      end
    end
  end

  context "all frames" do
    before :each do
      (Game::TOTOAL_FRAMES_COUNT-1).times do
        game.throw! knocked_pins: 5
        game.throw! knocked_pins: 3
      end
    end

    it "should not allow more throws" do
      game.throw! knocked_pins: 5
      game.throw! knocked_pins: 3
      expect(game.score).to eq 80
      expect(game.game_over?).to eq true
      expect { game.throw! knocked_pins: 3 }.to raise_error(GameError)
    end

    it "should handle strike in last frame" do
      game.throw! knocked_pins: 10
      expect(game.score).to eq 82
      expect(game.game_over?).to eq false

      expect { game.throw! knocked_pins: 3 }.not_to raise_error
      expect(game.score).to eq 85
      expect(game.game_over?).to eq false

      expect { game.throw! knocked_pins: 4 }.not_to raise_error
      expect(game.score).to eq 89
      expect(game.game_over?).to eq true
    end

    it "should handle spare in last frame" do
      game.throw! knocked_pins: 4
      game.throw! knocked_pins: 6
      expect(game.score).to eq 82
      expect(game.game_over?).to eq false

      expect { game.throw! knocked_pins: 3 }.not_to raise_error
      expect(game.score).to eq 85
      expect(game.game_over?).to eq true
    end
  end

  context "different test cases" do
    specify "2 strikes in a row" do
      game.throw! knocked_pins: 5
      game.throw! knocked_pins: 3
      expect(game.score).to eq 8

      game.throw! knocked_pins: 10
      expect(game.score).to eq 18

      game.throw! knocked_pins: 10
      expect(game.score).to eq 38

      game.throw! knocked_pins: 3
      expect(game.score).to eq 47
      game.throw! knocked_pins: 4
      expect(game.score).to eq 55
    end

    specify "with strikes and spares in a row" do
      game.throw! knocked_pins: 5
      game.throw! knocked_pins: 3
      expect(game.score).to eq 8

      game.throw! knocked_pins: 10
      expect(game.score).to eq 18

      game.throw! knocked_pins: 2
      expect(game.score).to eq 22
      game.throw! knocked_pins: 8
      expect(game.score).to eq 38

      game.throw! knocked_pins: 1
      expect(game.score).to eq 40
      game.throw! knocked_pins: 9
      expect(game.score).to eq 49

      game.throw! knocked_pins: 3
      expect(game.score).to eq 55
      game.throw! knocked_pins: 4
      expect(game.score).to eq 59
    end

    specify "with strike at last 2 frames" do
      (Game::TOTOAL_FRAMES_COUNT-2).times do
        game.throw! knocked_pins: 5
        game.throw! knocked_pins: 3
      end
      expect(game.score).to eq 64

      game.throw! knocked_pins: 10
      expect(game.score).to eq 74
      expect(game.game_over?).to eq false

      game.throw! knocked_pins: 10
      expect(game.score).to eq 94
      game.throw! knocked_pins: 5
      expect(game.score).to eq 104
      game.throw! knocked_pins: 3
      expect(game.score).to eq 107
      expect(game.game_over?).to eq true
    end

    specify "with spare at last frame" do
      (Game::TOTOAL_FRAMES_COUNT-1).times do
        game.throw! knocked_pins: 5
        game.throw! knocked_pins: 3
      end
      expect(game.score).to eq 72

      game.throw! knocked_pins: 4
      game.throw! knocked_pins: 6
      expect(game.score).to eq 82
      expect(game.game_over?).to eq false

      game.throw! knocked_pins: 7
      expect(game.score).to eq 89
      expect(game.game_over?).to eq true
    end

    specify "custome - youtube" do
      # simulating: 'https://www.youtube.com/watch?v=aBe71sD8o8c'
      game.throw! knocked_pins: 8
      game.throw! knocked_pins: 2
      expect(game.score).to eq 10
      expect(game.game_over?).to eq false

      game.throw! knocked_pins: 7
      game.throw! knocked_pins: 3
      expect(game.score).to eq 27
      expect(game.game_over?).to eq false

      game.throw! knocked_pins: 3
      game.throw! knocked_pins: 4
      expect(game.score).to eq 37
      expect(game.game_over?).to eq false

      game.throw! knocked_pins: 10
      expect(game.score).to eq 47
      expect(game.game_over?).to eq false

      game.throw! knocked_pins: 2
      game.throw! knocked_pins: 8
      expect(game.score).to eq 67
      expect(game.game_over?).to eq false

      game.throw! knocked_pins: 10
      expect(game.score).to eq 87
      expect(game.game_over?).to eq false

      game.throw! knocked_pins: 10
      expect(game.score).to eq 107
      expect(game.game_over?).to eq false

      game.throw! knocked_pins: 8
      game.throw! knocked_pins: 0
      expect(game.score).to eq 131
      expect(game.game_over?).to eq false

      game.throw! knocked_pins: 10
      expect(game.score).to eq 141
      expect(game.game_over?).to eq false

      game.throw! knocked_pins: 8
      game.throw! knocked_pins: 2
      expect(game.score).to eq 161
      game.throw! knocked_pins: 9
      expect(game.score).to eq 170
      expect(game.game_over?).to eq true
    end
  end
end
