import './Board.css';

import { useState } from 'react';
import { Box, Button, FormControl, FormHelperText, IconButton, InputAdornment, InputLabel, OutlinedInput } from '@material-ui/core';
import { TextField } from '@material-ui/core';
function Board() {
    let board = [
        [-1, , -3, , , , , ,],
        [, -2, , , , , , -8, -9],
        [, -1, , -4, , , , ,],
        [, , , , , , , ,],
        [, , , , -5, , , ,],
        [, , , , , -6, , ,],
        [, , -9, -5, , , -7, ,],
        [-8, -6, , , , , , ,],
        [, , , , , , -8, ,]];
    const index = [0, 1, 2, 3, 4, 5, 6, 7, 8];
    function HandleInput(e) {
        console.log(e.target.value);
        let position = e.target.id;
        let val = parseInt(e.target.value);
        board[position[1] - '0'][position[3] - '0'] = val;
    }
    function generateBoard() {
        //backend api
    }
    function validateBoard() {
        //backend api
    }
    return (
        <>
            <marquee style={{ color: 'blue', width: "75%" }}>lets play game....created by kevin bhanderi</marquee><br />

            <div className="Board" >
                <br />
                <div style={{ marginLeft: "2%", marginRight: "2%" }}>
                    <table style={{ border: "1px solid black" }}>
                        {
                            index.map((i) => {
                                return <>
                                    {
                                        <tr>
                                            {index.map((j) => {
                                                return <>
                                                    <td ><input id={'r' + i + 'j' + j} lable="" value={board[i][j] < 0 ? board[i][j] * -1 : board[i][j]} onChange={HandleInput} style={{ width: "25px", height: "25px", border: "1px solid black", fontSize: "15px", backgroundColor: board[i][j] < 0 ? "#86592d" : "#1" }} /></td>
                                                </>
                                            })}
                                        </tr>}
                                </>
                            })}
                    </table>
                </div>
            </div>
            <br />
            <div style={{ display: 'flex', justifyContent: 'space-evenly' }}>
                <Button
                    disableElevation
                    fullWidth
                    size="large"
                    type="submit"
                    variant="contained"
                    color="primary"
                    style={{ width: 'auto', alignItems: 'center' }}
                    onClick={generateBoard}
                >
                    Generate
                </Button>
                <TextField id="result" label="Not Yet Solved" variant="standard"
                    color="primary" disabled={true} size="small"></TextField>
                <Button
                    disableElevation
                    fullWidth
                    size="large"
                    type="submit"
                    variant="contained"
                    color="primary"
                    style={{ width: 'auto', alignItems: 'center' }}
                    onClick={validateBoard}
                >
                    Varify
                </Button>
            </div>
        </>
    )
}
export default Board;