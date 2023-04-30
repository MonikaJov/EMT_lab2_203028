import React from "react";

const manufacturers = (props) => { //authors
    return (
        <div className={"container mm-4 mt-5"}>
            <div className={"row"}>
                <div className={"table-responsive"}>
                    <table className={"table table-striped"}>
                        <thead>
                        <tr>
                            <th scope={"col"}>Name</th>
                            <th scope={"col"}>Surname</th>
                        </tr>
                        </thead>
                        <tbody>
                        {props.manufacturers.map((term) => { //authors
                            return (
                                <tr>
                                    <td>{term.name}</td>
                                    {/*//term.surname*/}
                                    <td>{term.address}</td>
                                </tr>
                            );
                        })}
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    );
}

export default manufacturers; //authors